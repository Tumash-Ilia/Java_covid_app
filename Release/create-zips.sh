#!/bin/bash
set -e

function save_docker_image(){
    IMAGE=$1
    ZIP_PATH=$2
    IMAGE_NAME=$(echo "$IMAGE" | rev | cut -d"/" -f1 | rev | sed 's/:/_/g')
    echo "Saving $IMAGE to zip file $ZIP_PATH/$IMAGE_NAME.zip ..."
    docker save $IMAGE | pv | cat > $ZIP_PATH/$IMAGE_NAME.zip
}

ZIP_FOLDER="export/zips"
mkdir -p $ZIP_FOLDER

docker pull registry.gitlab.com/sti-group/sti-app/frontend
docker pull registry.gitlab.com/sti-group/sti-app/backend

save_docker_image "registry.gitlab.com/sti-group/sti-app/frontend" $ZIP_FOLDER
save_docker_image "registry.gitlab.com/sti-group/sti-app/backend" $ZIP_FOLDER
