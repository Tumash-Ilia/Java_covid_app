# Authors: S. Woidig, M. Buntovskikh, I. Tumash, N. Vetsko

echo "Exporting logs from NC-19 backend..."

$BE_CONTAINER_NAME = $(docker ps | Select-String -Pattern "backend" | Out-String -Stream).split(" ")[1]
$BE_LOGS = $BE_CONTAINER_NAME + ":/home/sti/logs"
docker cp $BE_LOGS .
$MESSAGE = "Logs placed at " + $pwd + "\logs! Please zip this folder and send to authors!"
echo $MESSAGE
echo "Emails: simon.woidig@tul.cz, mariia.butovskikh@tul.cz, ilia.tumash@tul.cz, nataliia.vetsko@tul.cz"
