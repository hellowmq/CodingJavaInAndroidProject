package com.wenmq.clickcompiler;



import com.google.auto.service.AutoService;
import com.wenmq.clickanno.OnceClick;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class OnceClickProcessor extends AbstractProcessor {

    private Messager messager;
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //获取proxyMap
        Messager messager = processingEnv.getMessager();
        int size = roundEnv.getElementsAnnotatedWith(OnceClick.class).size();
        messager.printMessage(Diagnostic.Kind.NOTE,
                "Annotation class size = " + size);
        for (Element element : roundEnv.getElementsAnnotatedWith(OnceClick.class)) {
            PackageElement packageElement = (PackageElement) element
                    .getEnclosingElement();
            //获取该注解所在类的包名
            String packageName = packageElement.getQualifiedName().toString();
            TypeElement classElement = (TypeElement) element;
            //获取该注解所在类的类名
            String className = classElement.getSimpleName().toString();
            //获取该注解所在类的全类名
            String fullClassName = classElement.getQualifiedName().toString();
            //获取该注解的值
            int value = classElement.getAnnotation(OnceClick.class).value();
            String name = classElement.getAnnotation(OnceClick.class).value()+"";
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : packageName = " + packageName);
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : className = " + className);
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : fullClassName = " + fullClassName);
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : value = " + value);
        }
        Map<String, ProxyInfo> proxyMap = getProxyMap(roundEnv);
        System.out.println("LifecycleClassVisitor : process proxyMap ----> ");

        //遍历proxyMap，并生成代码
        for (String key : proxyMap.keySet()) {
            ProxyInfo proxyInfo = proxyMap.get(key);
            writeCode(proxyInfo);
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(OnceClick.class.getCanonicalName());
        return types;
    }

    private Map<String, ProxyInfo> getProxyMap(RoundEnvironment roundEnv) {
        Map<String, ProxyInfo> proxyMap = new HashMap<>();
        for (Element element : roundEnv.getElementsAnnotatedWith(OnceClick.class)) {
            //target相同只能强转。不同使用getEnclosingElement
            ExecutableElement executableElement = (ExecutableElement) element;
            TypeElement classElement = (TypeElement) element
                    .getEnclosingElement();

            PackageElement packageElement = elementUtils.getPackageOf(classElement);


            String fullClassName = classElement.getQualifiedName().toString();
            String className = classElement.getSimpleName().toString();
            String packageName = packageElement.getQualifiedName().toString();
            String methodName = executableElement.getSimpleName().toString();
            int viewId = executableElement.getAnnotation(OnceClick.class).value();

            print("fullClassName: "+ fullClassName +
                ",  methodName: "+methodName +
                    ",  viewId: "+viewId);

            OnceMethod onceMethod = new OnceMethod(viewId,methodName,getMethodParameterTypes(executableElement));

            ProxyInfo proxyInfo = proxyMap.get(fullClassName);
            if (proxyInfo != null) {
                proxyInfo.addMethod(onceMethod);
            } else {
                proxyInfo = new ProxyInfo(packageName, className);
                proxyInfo.setTypeElement(classElement);
                proxyInfo.addMethod(onceMethod);
                proxyMap.put(fullClassName, proxyInfo);
            }
        }
        return proxyMap;
    }

    /**
     * 取得方法参数类型列表
     */
    private List<String> getMethodParameterTypes(ExecutableElement executableElement) {
        List<? extends VariableElement> methodParameters = executableElement.getParameters();
        if (methodParameters.size()==0){
            return null;
        }
        List<String> types = new ArrayList<>();
        for (VariableElement variableElement : methodParameters) {
            TypeMirror methodParameterType = variableElement.asType();
            if (methodParameterType instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) methodParameterType;
                methodParameterType = typeVariable.getUpperBound();
            }
            types.add(methodParameterType.toString());
        }
        return types;
    }

    private void writeCode(ProxyInfo proxyInfo) {
        try {
            System.out.println("LifecycleClassVisitor : 匹配FragmentActivity ----> ");
            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                    proxyInfo.getProxyClassFullName(),
                    proxyInfo.getTypeElement());
            Writer writer = jfo.openWriter();
            writer.write(proxyInfo.generateJavaCode());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            error(proxyInfo.getTypeElement(),
                    "Unable to write injector for type %s: %s",
                    proxyInfo.getTypeElement(), e.getMessage());
        } catch (OnceClickException e){
            error(proxyInfo.getTypeElement(),
                    "The use of irregular %s: %s",
                    proxyInfo.getTypeElement(), e.getMessage());
        }
    }

    private void print(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
    }

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        messager.printMessage(Diagnostic.Kind.ERROR, message, element);
    }



}
