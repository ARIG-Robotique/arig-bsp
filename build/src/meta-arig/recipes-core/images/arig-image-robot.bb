SUMMARY = "Base image for ARIG robots."
LICENSE = "MIT"
inherit core-image

SPLASH = "psplash-arig"

ENABLE_I2C = "1"
GPU_MEM = "128"

# Disable rainbow after gpu firmware is loaded
#DISABLE_SPLASH = "1"

IMAGE_FEATURES += "splash"
IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL_append = " i2c-tools"
IMAGE_INSTALL_append = " arig-bellsoft-jre"

#KERNEL_MODULE_AUTOLOAD:rpi += "i2c-dev i2c-bcm2708"

# Add features
DISTRO_FEATURES += "wifi"

# Remove uneeded features
DISTRO_FEATURES_remove = " \
        3g \
        acl \
        alsa \
        bluetooth \
        gobject-introspection-data \
        ipv6 \
        nfc \
        nfs \
        opengl \
        pci \
        pcmcia \
        pulseaudio \
        usbgadget \
        vulkan \
        wayland \
        x11 \
        xattr \
        zeroconf \
"