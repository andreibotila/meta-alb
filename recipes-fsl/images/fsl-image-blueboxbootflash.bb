#
# We also want to permit NOR flash image generation.
# We are using the new flashimage class to get a full NOR flash image
#
# <Heinz.Wrobel@nxp.com>
#

require fsl-image-emptyrootfs.inc

# The following two lines are advanced magic as it seems.
# IMAGE_FSTYPES is of sufficient global value to all dependencies that
# you cannot  "just" build a flashimage that contains an ext2.gz by
# specifying IMAGE_FSTYPES = "flashimage". If you try to do that, the
# dependency will not get built as ext2.gz. So IMAGE_FSTYPES needs to
# contains all types that the dependency chain may require.
# To then limit a given image to only a specific resulting image type
# in the deploy directory, we need to ensure that we mask all the
# others. That has to be immediately evaluated however because
# otherwise, we'd get the value that the second line sets.
IMAGE_TYPES_MASKED := "${IMAGE_FSTYPES}"
IMAGE_FSTYPES += "flashimage"

inherit distro_features_check

# We want easy installation of the BlueBox image
DEPENDS += "bbdeployscripts"

COMPATIBLE_MACHINE = "ls2084abbmini"