# Source d'inspiration
# https://layers.openembedded.org/layerindex/recipe/5525/
# https://git.yoctoproject.org/meta-oracle-java/tree/recipes-devtools/oracle-java
# https://github.com/zulu-openjdk/meta-zulu-java/blob/master/recipes-devtools/zulu-jdk_1.8.0.bb
#
# Sample URL Download
# https://download.bell-sw.com/java/11.0.13+8/bellsoft-jre11.0.13+8-linux-aarch64.tar.gz 
# https://download.bell-sw.com/java/13.0.2+9/bellsoft-jre13.0.2+9-linux-aarch64.tar.gz

def get_suffix(d):
    target_arch = d.getVar('TARGET_ARCH', True)
    target_fpu = d.getVar('TARGET_FPU', True)
    suffix = ''
    if target_arch == "arm":
        if target_fpu in [ 'hard' ]:
            suffix = "arm32-vfp-hflt"
    elif target_arch in ["aarch64", "i586"]:
        suffix = target_arch
    
    if suffix != '':
        return suffix

    raise bb.parse.SkipPackage("Target architecture '%s' or target FPU '%s' is not supported by the meta-arig layer" % target_arch, target_fpu)


def get_md5(version, suffix):
    if version == "11.0.13+8":
        if suffix == "aarch64":
            # d101e7a646999410433323e7fdb186a4  bellsoft-jre11.0.13+8-linux-aarch64.tar.gz
            return "d101e7a646999410433323e7fdb186a4"
        elif suffix == "arm32-vfp-hflt":
            # 214425972e627623c7d5676841e332cb  bellsoft-jre11.0.13+8-linux-arm32-vfp-hflt.tar.gz
            return "214425972e627623c7d5676841e332cb"
        elif suffix == "i586":
            # 223431b96c9c69f23d3ec7ef761303be  bellsoft-jre11.0.13+8-linux-i586.tar.gz
            return "223431b96c9c69f23d3ec7ef761303be"

    elif version == "13.0.2+9":
        if suffix == "aarch64":
            # 097695e79ee59fcac3e3d68e6168100e  bellsoft-jre13.0.2+9-linux-aarch64.tar.gz
            return "097695e79ee59fcac3e3d68e6168100e"
        elif suffix == "arm32-vfp-hflt":
            # 5336e0b0c440a14493fc76581754406c  bellsoft-jre13.0.2+9-linux-arm32-vfp-hflt.tar.gz
            return "5336e0b0c440a14493fc76581754406c"
        elif suffix == "i586":
            # c8480f0c10fca49cc684400bae514470  bellsoft-jre13.0.2+9-linux-i586.tar.gz
            return "c8480f0c10fca49cc684400bae514470"
    
    raise bb.parse.SkipPackage("Unsupported version '%s' or suffix '%s' to get md5sum" % version, suffix)


def get_sha256(version, suffix):
    if version == "11.0.13+8":
        if suffix == "aarch64":
            # 6ceaa19b3f62e565ca55706d14e2d86b918f6e2aad7951bf27afb05c5aac35f8  bellsoft-jre11.0.13+8-linux-aarch64.tar.gz
            return "6ceaa19b3f62e565ca55706d14e2d86b918f6e2aad7951bf27afb05c5aac35f8"
        elif suffix == "arm32-vfp-hflt":
            # cc4e843d49e1ec10fa3640da8aecbfd4f01b89bcc0ffc3b9c296d786e230a4de  bellsoft-jre11.0.13+8-linux-arm32-vfp-hflt.tar.gz
            return "cc4e843d49e1ec10fa3640da8aecbfd4f01b89bcc0ffc3b9c296d786e230a4de"
        elif suffix == "i586":
            # 6c220c8d86c096717842df1a66884a5579a941888dfd8194225f251521675b25  bellsoft-jre11.0.13+8-linux-i586.tar.gz
            return "6c220c8d86c096717842df1a66884a5579a941888dfd8194225f251521675b25"
    
    elif version == "13.0.2+9":
        if suffix == "aarch64":
            # 8e483ef876d9a50c2d2f81ffc678102e5c8fd470b4ec526a6f55ae5f0a1d7151  bellsoft-jre13.0.2+9-linux-aarch64.tar.gz
            return "8e483ef876d9a50c2d2f81ffc678102e5c8fd470b4ec526a6f55ae5f0a1d7151"
        elif suffix == "arm32-vfp-hflt":
            # b8ea4a8379b902e0990a953dfcd8df4b6ed1a40d007c323932f84d691127fc0b  bellsoft-jre13.0.2+9-linux-arm32-vfp-hflt.tar.gz
            return "b8ea4a8379b902e0990a953dfcd8df4b6ed1a40d007c323932f84d691127fc0b"
        elif suffix == "i586":
            # b4abfe1f1478b438ede390b0ec509cd7b1a26a754f51c605c0ad0e7d0f69d3bc  bellsoft-jre13.0.2+9-linux-i586.tar.gz
            return "b4abfe1f1478b438ede390b0ec509cd7b1a26a754f51c605c0ad0e7d0f69d3bc"

    raise bb.parse.SkipPackage("Unsupported version '%s' or suffix '%s' to get sh256sum" % version, suffix)


BELLSOFT_VERSION ?= "13.0.2"
BELLSOFT_PATCH_VERSION ?= "9"
BELLSOFT_FULL_VERSION := "${BELLSOFT_VERSION}+${BELLSOFT_PATCH_VERSION}"

BELLSOFT_SUFFIX = "${@get_suffix(d)}"
BELLSOFT_DOWNLOAD_URL = "https://download.bell-sw.com/java/${BELLSOFT_FULL_VERSION}/bellsoft-jre${BELLSOFT_FULL_VERSION}-linux-${BELLSOFT_SUFFIX}.tar.gz"
BELLSOFT_DOWNLOAD_URL_MD5 = "${@get_md5(BELLSOFT_FULL_VERSION, BELLSOFT_SUFFIX)}"
BELLSOFT_DOWNLOAD_URL_SHA256 = "${@get_sha256(BELLSOFT_FULL_VERSION, BELLSOFT_SUFFIX)}"

SUMMARY = "Bellsoft Liberica Java Runtime (JRE) binaries"

LICENSE = "CLOSED"

SRC_URI = "${BELLSOFT_DOWNLOAD_URL}"
SRC_URI[md5sum] = "${BELLSOFT_DOWNLOAD_URL_MD5}"
SRC_URI[sha256sum] = "${BELLSOFT_DOWNLOAD_URL_SHA256}"

S = "${WORKDIR}"
PV = "${BELLSOFT_VERSION}"
PR = "r${BELLSOFT_PATCH_VERSION}"

do_install () {
  install -d -m 0755 ${D}${datadir}/bellsoft-jre-${BELLSOFT_FULL_VERSION}_${BELLSOFT_SUFFIX}
  cp -a ${S}/jre-${BELLSOFT_VERSION}/* ${D}${datadir}/bellsoft-jre-${BELLSOFT_FULL_VERSION}_${BELLSOFT_SUFFIX}
  chown -R root:root ${D}${datadir}/bellsoft-jre-${BELLSOFT_FULL_VERSION}_${BELLSOFT_SUFFIX}
  
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/bellsoft-jre-${BELLSOFT_FULL_VERSION}_${BELLSOFT_SUFFIX}/bin/java ${D}${bindir}/java
}

# This provide some of shared library, but must be used only by this java virtual machine
EXCLUDE_FROM_SHLIBS = "1"

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "arig-bellsoft-jre"
PROVIDES += "virtual/java"
