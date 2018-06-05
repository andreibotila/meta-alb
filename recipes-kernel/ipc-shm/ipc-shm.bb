# SPDX-License-Identifier:	BSD-3-Clause
#
# Copyright 2018 NXP
#

SUMMARY = "Adds support for Inter-Process(or) Communication over Shared Memory (ipc-shm)"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit module

SRC_URI = "git://source.codeaurora.org/external/autobsps32/ipcf/ipc-shm;branch=master;protocol=https"
SRCREV = "c5f4330dae126ea82eb267f41a7038a4767a8caf"

S = "${WORKDIR}/git"
DESTDIR="${D}"
EXTRA_OEMAKE_append = " -C ./ipc-shm-sample INSTALL_DIR=${DESTDIR} KERNELDIR=${KBUILD_OUTPUT} "
MODULES_MODULE_SYMVERS_LOCATION = "ipc-shm-dev"

PROVIDES = "kernel-module-ipc-shm${KERNEL_MODULE_PACKAGE_SUFFIX}"
RPROVIDES_${PN} = "kernel-module-ipc-shm${KERNEL_MODULE_PACKAGE_SUFFIX}"
PROVIDES += "kernel-module-ipc-shm-sample${KERNEL_MODULE_PACKAGE_SUFFIX}"
RPROVIDES_${PN} += "kernel-module-ipc-shm-sample${KERNEL_MODULE_PACKAGE_SUFFIX}"
PROVIDES += "kernel-module-ipc-shm-dev${KERNEL_MODULE_PACKAGE_SUFFIX}"
RPROVIDES_${PN} += "kernel-module-ipc-shm-dev${KERNEL_MODULE_PACKAGE_SUFFIX}"  

FILES_${PN} += "${base_libdir}/*"

COMPATIBLE_MACHINE = "s32g275sim"
