# Authors: S. Woidig, M. Buntovskikh, I. Tumash, N. Vetsko
echo "Stopping NC-19..."

# Run docker-compose not depending on location
Set-Variable -Name "CURR_DIR" -Value $("" + $pwd)

cd $PSScriptRoot; docker-compose down; cd $CURR_DIR

echo "Application is STOPPED!"
