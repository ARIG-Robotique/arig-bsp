#!/usr/bin/env bash
POKY_VERSION="hardknott"
YOCTO_VERSION="3.3.1"
BUILD_DIR=$(pwd)
EXTERNAL_SRC="external-src"

mkdir -p ${EXTERNAL_SRC}

# Récupération de Poky / Yocto #
################################
cd ${BUILD_DIR}/${EXTERNAL_SRC}
if [ ! -d poky ] ; then
  echo "Clone Poky ${POKY_VERSION}"
  git clone -b ${POKY_VERSION} git://git.yoctoproject.org/poky.git
fi

cd ${BUILD_DIR}/${EXTERNAL_SRC}/poky
git checkout yocto-${YOCTO_VERSION}

# Meta Raspberry PI #
#####################
cd ${BUILD_DIR}/${EXTERNAL_SRC}
if [ ! -d meta-raspberrypi ] ; then
  echo "Clone meta Raspberry PI"
  git clone -b ${POKY_VERSION} git://git.yoctoproject.org/meta-raspberrypi.git
fi

# Meta QT 5 #
#############
cd ${BUILD_DIR}/${EXTERNAL_SRC}
if [ ! -d meta-qt5 ] ; then
  echo "Clone meta QT5"
  git clone -b ${POKY_VERSION} https://github.com/meta-qt5/meta-qt5.git
fi

# -------------------------------------------------------- #
# ------------------------- BUILD ------------------------ #
# -------------------------------------------------------- #

cd ${BUILD_DIR}

# Qemu
# source poky/oe-init-build-env bsp-arig
# for machine in qemux86-64 qemuarm64 ; do
#   MACHINE=${machine} bitbake core-image-minimal
# done
# cd ${BUILD_DIR}

# Raspberry PI BSP
for bsp in bsp-arig-nerell bsp-arig-odin ; do
  cd ${BUILD_DIR}
  source ${EXTERNAL_SRC}/poky/oe-init-build-env ${bsp}
  bitbake-layers show-layers
  bitbake arig-image-robot
done

# -------------------------------------------------------- #

cd ${BUILD_DIR}
bash -lE
