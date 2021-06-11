# hatsdf_base_design

Supported Xilinx Version: 2020.1. This base design is based on the [official Xilinx documentation (Sec. VIII)](https://www.xilinx.com/support/documentation/sw_manuals/xilinx2019_2/ug1393-vitis-application-acceleration.pdf)

## Before you begin

* source your petalinux installation: source `<PETALINUNX_INSTALL_DIR>/settings.sh`
* source xsct and other xilinx tools: source `<VITIS_INSTALL_DIR>/2020.1/settings64.sh`
* copy board files from `htsdf_base_design/board_files` to `<VIVADO_INSTALL_DIR>/2020.2/settings64.sh`
* Set vivado license as environment variable
* You need 50Gb+ of disc space

## Build

Use the provided Makefile: `make` or `make all`

The individual build targets are explained below

## Built Targets - Explained

### Create Hardware description from Vivado

The base_design makefile contains two targets to build the Vivado project:

* vivado_create: Create project from FastSense_base.tcl
* vivado_build: Execute build_vivado.tcl that starts synthesis and implementation for the created project and exports the hardware platform as /FastSense_base/FastSense_base.xsa

### Build linux image

The makefile contains three targets to build the Petalinux project (50-100GB free disk space required):

* petalinux_config: Import/Update the hardware platform (FastSense_base.xsa) and configure the project
* petalinux_build: Build the image.
* petalinux_build_sdk: Build SDK tools and sysroot

The default user and passwd are `root:CHANGEME`

### Build platform

The makefile contains three targets to build the platform project:

* copy_boot: Copy the boot components (FSBL, U-Boot, PMUFW,Kernel) from the Petalinux project to the platform source directories
* sysroot: Extract the sysroot to the platform source directory
* platform_create: Generate and build the platform project. The exported Platform can be found in /platform/FastSense_platform/export/FastSense_platform/

Message "WARNING: Failed to copy boost::filesystem::copy_file: Permission denied: ..." can be ignored.

At this point, you ready to compile the complete [FastSense project](TODO).

## Elements

### Vivado Project
* The project must be generated with the FastSense_base.tcl script.
* A clocking wizard with 2 clock outputs is used:
    |Clock ID|Frequency (MHz)|
    |:-------|--------------:|
    | 0 (default) | 100      |
    | 1           | 200      |
* Board defaults from Trenz are used, execpt Displayport, CAN, Live Audio, all AXI-Master/Slave interfaces and clock source PL1 are deactivated.
* The SD0 interface is disabled (otherwise there are conflicts with the emulation and QEMU can not boot)
* Added AXI Interrupt Controller to Block Design to project to support interrupts instead of polling (dynamic_postlink.tcl not longer needed)
* The following memory ports are enabled for the "Platform interfaces" view:
    |PS Port Name|SPTAG|memory (address segment)|
    |:-----------|:---:|-----------------------:|
    |M_AXI_HPM0_FPD|HPM0|zynq_ultra_ps_e_0 HPM0_DDR_LOW|
    |M_AXI_HPM1_FPD|	HPM1|	zynq_ultra_ps_e_0 HPM1_DDR_LOW|
    |S_AXI_HPC0_FPD|	HPC0|	zynq_ultra_ps_e_0 HPC0_DDR_LOW|
    |S_AXI_HPC1_FPD|	HPC1|	zynq_ultra_ps_e_0 HPC1_DDR_LOW|
    |S_AXI_HP0_FPD|	HP0	|zynq_ultra_ps_e_0 HP0_DDR_LOW|
    |S_AXI_HP1_FPD|	HP1	|zynq_ultra_ps_e_0 HP1_DDR_LOW|
    |S_AXI_HP2_FPD|	HP2	|zynq_ultra_ps_e_0 HP2_DDR_LOW|
    |S_AXI_HP3_FPD|	HP3	|zynq_ultra_ps_e_0 HP3_DDR_LOW|
* There is an AXI GPIO for buttons/LEDs. It has two ports with two pins each. The first one is for inputs and the second for outputs. Additionally for the external circuit, a constant must be applied to the type pins that set the mode of the level shifter.
### Petalinux Project
1. Added packages for Vitis:
    * petalinux-packagegroup-xrt
    * petalinux-packagegroup-xrt-dev
2. User packages
    * libphidget21 v2.1.9.20190409: libusb driver for the Phidget IMU 
    * dropbearkey: fixed host key for SSH. Normally a new key is generated after every boot because a INITRD is used as root file system. This causes an error when a client tries to connect to the board because the IP address stays the same and the user must manually remove it from the known_hosts file.
    * HighFive v2.2.2
    * lib-hdf5-dev v1.10
    * xrt-env: sets XILINX_XRT to /usr by default
3. Additional packages
    * cppzmq v4.4.1 with zeromq v4.3.2
    * libeigen v3.3.7
    * gdb/gdbserver
    * sudo
    * (sudoers)
    * vim
    * dropbear
    * libgpiod
4. Mount points
    * SATA drive is mounted to /data
    * SD card is mounted to /mnt
5. Static IP address: 192.168.1.123
6. Users:
    * root: CHANGEME
### Platform Project

The platform project is generated with the create_platform.tcl file (see). The platform contains a Linux domain with OpenCL as runtime based on the Petalinux outputs.