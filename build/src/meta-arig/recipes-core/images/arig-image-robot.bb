SUMMARY = "Base image for ARIG robots."
LICENSE = "MIT"
inherit core-image

SPLASH = "psplash-arig"

ENABLE_I2C = "1"

IMAGE_FEATURES += "splash"
IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL_append = " i2c-tools"
IMAGE_INSTALL_append = " arig-bellsoft-jre"
