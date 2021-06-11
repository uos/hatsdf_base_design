#
# This file is the sudoers recipe.
#

SUMMARY = "Add users to sudoers"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://student \
	"

S = "${WORKDIR}"

do_install() {
	     install -m 0750 -d ${D}${sysconfdir}/sudoers.d
	     install -m 0440 ${S}/student ${D}${sysconfdir}/sudoers.d/student
}
