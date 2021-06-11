SUMMARY = "HighFive hdf5 wrapper"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "https://github.com/BlueBrain/HighFive/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "b6eff1af99a26f60f38ae177959ad37f"

DEPENDS += "hdf5 boost"
RDEPENDS_${PN} += "hdf5 boost"

S = "${WORKDIR}/HighFive-${PV}"

PACKAGES = "${PN}-dev"

RDEPENDS_${PN}-dev = "hdf5-dev boost-dev"

inherit cmake

EXTRA_OECMAKE = " \
    -DHIGHFIVE_UNIT_TESTS=OFF \
    -DHIGHFIVE_EXAMPLES=OFF \
"

do_install_append() {
    install -d ${D}/usr/share/cmake/HighFive
    mv ${D}/usr/share/HighFive/CMake/* ${D}/usr/share/cmake/HighFive/
    rm -rf ${D}/usr/share/HighFive
}
