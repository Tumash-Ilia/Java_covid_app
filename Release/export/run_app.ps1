# Authors: S. Woidig, M. Buntovskikh, I. Tumash, N. Vetsko
echo "Launching NC-19..."

# Load docker images from zip files
echo "Loading backend image"
docker load -i $PSScriptRoot\zips\backend.zip
echo "Loading frontend image"
docker load -i $PSScriptRoot\zips\frontend.zip

# Run docker-compose not depending on location
Set-Variable -Name "CURR_DIR" -Value $("" + $pwd)

cd $PSScriptRoot; docker-compose up -d; cd $CURR_DIR

echo "Application is RUNNING!"
