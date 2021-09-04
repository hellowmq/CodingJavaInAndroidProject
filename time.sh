# Check Email
echo "Check Email"
EMAIL=$(git config --local user.email)
if [[ $EMAIL == "wenmq47@gmail.com" ]]; then
    echo "Check Email Succeed"
else
    echo "Check Email Failed"
fi
echo "------------------------------------"

# Show Time
echo "Show Time"
date "+%Y-%m-%d %H:%M:%S"
echo "------------------------------------"

## Check Network
#echo "Check Network"
#ssh -T git@github.com
#echo "------------------------------------"