DESCRIPTION = "Unique technology suite that makes possible the management of extremely large and complex data collections"
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
SUMMARY = "HDF5 is a data model, library, and file format for storing and managing data"
HOMEPAGE = "http://caffe.berkeleyvision.org/"
LICENSE = "BSD"
PRIORITY= "optional"
SECTION = "libs"
PR = "r0"

RDEPENDS_${PN} = "zlib"

LIC_FILES_CHKSUM = "file://COPYING;md5=106bcadf06f1d36b417baffe40ded960"

inherit cmake

SRC_URI = " \
    https://support.hdfgroup.org/ftp/HDF5/current/src/${PN}-${PV}.tar.bz2 \
    file://H5lib_settings.c \
    file://H5Tinit.c \
    file://lib_settings.patch \
"

SRC_URI[md5sum] = "7c19d6b81ee2a3ba7d36f6922b2f90d3"
SRC_URI[sha256sum] = "68d6ea8843d2a106ec6a7828564c1689c7a85714a35d8efafa2fee20ca366f44"

FILES_${PN} += "/usr/lib/libhdf5.settings"

EXTRA_OECMAKE = " \
    -DBUILD_TESTING=OFF \
    -DHDF5_BUILD_EXAMPLES=OFF \
    -DHDF5_USE_PREGEN=ON \
    -DHDF5_USE_PREGEN_DIR='${WORKDIR}' \
    -DTEST_LFS_WORKS_RUN=0 \
    -DTEST_LFS_WORKS_RUN__TRYRUN_OUTPUT=0 \
    -DH5_PRINTF_LL_TEST_RUN=1 \
    -DH5_PRINTF_LL_TEST_RUN__TRYRUN_OUTPUT='8' \
    -DH5_LDOUBLE_TO_LONG_SPECIAL_RUN=0 \
    -DH5_LDOUBLE_TO_LONG_SPECIAL_RUN__TRYRUN_OUTPUT= \
    -DH5_LONG_TO_LDOUBLE_SPECIAL_RUN=0 \
    -DH5_LONG_TO_LDOUBLE_SPECIAL_RUN__TRYRUN_OUTPUT= \
    -DH5_LDOUBLE_TO_LLONG_ACCURATE_RUN=0 \
    -DH5_LDOUBLE_TO_LLONG_ACCURATE_RUN__TRYRUN_OUTPUT= \
    -DH5_LLONG_TO_LDOUBLE_CORRECT_RUN=0 \
    -DH5_LLONG_TO_LDOUBLE_CORRECT_RUN__TRYRUN_OUTPUT= \
    -DH5_DISABLE_SOME_LDOUBLE_CONV_RUN=0 \
    -DH5_DISABLE_SOME_LDOUBLE_CONV_RUN__TRYRUN_OUTPUT= \
    -DH5_NO_ALIGNMENT_RESTRICTIONS_RUN=0 \
    -DH5_NO_ALIGNMENT_RESTRICTIONS_RUN__TRYRUN_OUTPUT= \
"

do_install_append() {
    rm -f ${D}/usr/share/COPYING
    rm -f ${D}/usr/share/RELEASE.txt
    rm -f ${D}/usr/share/USING_HDF5_CMake.txt
}
