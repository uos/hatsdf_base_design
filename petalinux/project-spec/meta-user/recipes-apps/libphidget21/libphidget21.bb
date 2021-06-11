SUMMARY = "libphidget21 Phidget Driver"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://libphidget-2.1.9.20190409/library_version \
    file://libphidget_2.1.9.20190409.tar.gz \
    "

DEPENDS += "libusb1"
RDEPENDS_${PN} += "libusb1"
        
S = "${WORKDIR}/libphidget-2.1.9.20190409"

inherit autotools pkgconfig

FILES_${PN} += "${libdir}/*.so ${libdir}/*.so.*"
