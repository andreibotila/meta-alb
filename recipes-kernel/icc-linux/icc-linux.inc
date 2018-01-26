
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8c0323a6a5e94cd2a72b8fb935e90fcd"

inherit module

SRC_URI = "git://source.codeaurora.org/external/autobsps32/icc-linux;branch=alb/master;protocol=https"

export INSTALL_MOD_DIR="kernel/icc-linux"

DESTDIR="${D}"
export DESTDIR

LOCAL_CONFIGDIR ?= "output"
SRC_CONFIGDIR = "${THISDIR}/${LOCAL_CONFIGDIR}"
export SRC_CONFIGDIR

S = "${WORKDIR}/git"
DEST_CONFIGDIR="${S}/release/iccs32v234/sample/config/"
export DEST_CONFIGDIR

FILESEXTRAPATHS_prepend := "${SRC_CONFIGDIR}:"

# ICC Configuration is generated by Tresos in the following tree structure:
# <icc_project>
#    output
#        include \ ICC_Config.h
#        src \ ICC_Config.c
# Users may simply copy the "output" folder into current directory, over existing
# "output" folder, in order to replace default configuration with a custom one.
SRC_URI += "\
    file://src/ICC_Config.c \
    file://include/ICC_Config.h \
"

do_configure() {
    # copy configuration files desired defconfig so we pick it up for the real kernel_do_configure
    cp -f "${SRC_CONFIGDIR}/src/ICC_Config.c" "${DEST_CONFIGDIR}/src/ICC_Config.c"
    cp -f "${SRC_CONFIGDIR}/include/ICC_Config.h" "${DEST_CONFIGDIR}/include/ICC_Config.h"
}