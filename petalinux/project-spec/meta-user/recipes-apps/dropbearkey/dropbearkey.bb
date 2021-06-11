#
# This file is the dropbearkey recipe.
#

SUMMARY = "Add dropbear key"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://dropbear_rsa_host_key"

do_install() {
	     install -d ${D}${sysconfdir}/dropbear
	     install -m 0644 ${WORKDIR}/dropbear_rsa_host_key ${D}${sysconfdir}/dropbear/dropbear_rsa_host_key
}
