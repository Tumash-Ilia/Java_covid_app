#!/bin/bash
set -e

echo "Zipping images..."
./create-zips.sh

echo "Creating release zip..."
cd ./export && zip -r ../nc-19.zip *

echo "DONE!"