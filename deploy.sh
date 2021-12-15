#!/usr/bin/env bash

BSP_NAME=$(ls -d build/bsp-arig-*/ | fzf --prompt "Choix du BSP : ")
MACHINE=$(ls -d ${BSP_NAME}tmp/deploy/images/*/ | fzf --prompt "Choix de la machine : ")
IMAGE=$(ls -d ${MACHINE}/*.wic.bz2 | fzf --prompt "Choix de l'image : ")

echo "BSP     : ${BSP_NAME}"
echo "MACHINE : ${MACHINE}"
echo "IMAGE   : ${IMAGE}"

bzcat ${IMAGE} | pv -cN zcat > ${1}
