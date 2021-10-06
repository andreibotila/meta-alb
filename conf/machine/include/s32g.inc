require conf/machine/include/s32-gen1-board.inc

KERNEL_DEFCONFIG ?= "s32gen1_defconfig"

MACHINEOVERRIDES =. "s32g:"

UBOOT_CONFIG ?= "sdcard"
UBOOT_CONFIG[sdcard] = "${UBOOT_DEFCONFIG},,u-boot.${UBOOT_SUFFIX}"

# prepend 'qspi' to keep the default deploy/u-boot-<MACHINE>.s32 to point to sdcard
UBOOT_CONFIG_prepend = "qspi "
UBOOT_CONFIG[qspi] = "${UBOOT_FLASH_DEFCONFIG},,u-boot.${UBOOT_SUFFIX}"

# Enable ATF if m7boot is not enabled.
# When m7boot is enabled, the needed ATF settings are included from m7-boot.inc
INC_FILE="${@bb.utils.contains('DISTRO_FEATURES', 'm7boot', '', 'conf/machine/include/atf.inc', d)}"
require ${@bb.utils.contains('DISTRO_FEATURES', 'atf', '${INC_FILE}', '', d)}