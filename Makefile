all: vivado_create vivado_build petalinux_config petalinux_build petalinux_build_sdk copy_boot sysroot platform_create

# Create Vivado project with tcl
vivado_create:
	vivado -mode batch -source FastSense_base.tcl -notrace

# Build Vivado project and export XSA file
vivado_build:
	vivado -mode batch -source build_vivado.tcl -notrace

# Configure Petalinux project with exported XSA
petalinux_config:
	[ -d "petalinux/.petalinux" ] || mkdir petalinux/.petalinux
	[ -d "petalinux/project-spec/hw-description" ] || mkdir petalinux/project-spec/hw-description
	petalinux-config -p ./petalinux --get-hw-description=./FastSense_base --silentconfig

# Build Petalinux project
petalinux_build:
	petalinux-build -p ./petalinux

# Build SDK (sysroot) for Petalinux project
petalinux_build_sdk:
	petalinux-build -p ./petalinux --sdk

# Copy Petalinux output files for platform generation
copy_boot:
	# Boot
	mkdir -p platform/boot/
	cp petalinux/images/linux/bl31.elf platform/boot/
	cp petalinux/images/linux/pmufw.elf platform/boot/
	cp petalinux/images/linux/u-boot.elf platform/boot/
	cp petalinux/images/linux/zynqmp_fsbl.elf platform/boot/fsbl.elf
	# Image
	mkdir -p platform/image/
	cp petalinux/images/linux/image.ub platform/image/
	cp petalinux/images/linux/boot.scr platform/image/
	# Emulation
	mkdir -p platform/emulation/
	cp petalinux/images/linux/bl31.elf platform/emulation/
	cp petalinux/images/linux/u-boot.elf platform/emulation/
	cp petalinux/images/linux/pmufw.elf platform/emulation/

# Extract sysroot to platform folder
sysroot:
	rm -rf platform/sysroots
	petalinux/images/linux/sdk.sh -d platform/ -y

# Create platform with tcl
platform_create:
	rm -rf platform/FastSense_platform
	xsct -eval "source create_platform.tcl"

clean:
	rm -rf FastSense_base || true
	rm platform/boot/* || true
	rm platform/emulation/* || true
	rm -rf platform/sysroots || true
	rm platform/*xilinx-linux || true
	rm -rf petalinux/build || true
	rm -rf petalinux/components || true
	rm -rf petalinux/images || true
	rm -rf petalinux/project-spec/hw-description/* || true
	rm -rf petalinux/project-spec/meta-plnx-generated || true
