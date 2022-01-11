SUMMARY = "Base image for ARIG robots."
LICENSE = "MIT"
inherit core-image

SPLASH = "psplash-arig"

# Raspberry Pi specific #
# --------------------- #

# Disable rainbow after gpu firmware is loaded
#DISABLE_SPLASH = "1"

# Core frequency
CORE_FREQ = "500"

GPU_MEM = "128"

ENABLE_I2C = "1"
ENABLE_UART = "1"

RPI_EXTRA_CONFIG = "core_freq_min=500"

# ---------------------------------- #

IMAGE_FEATURES += "splash"
IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL_append = " i2c-tools"
IMAGE_INSTALL_append = " arig-bellsoft-jre"

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