# Create platform
platform create -name {FastSense_platform} -hw {./FastSense_base/FastSense_base.xsa} -out {./platform} -desc {FastSense Platform} -no-boot-bsp

# Create domain and set configuration
domain create -name {linux_domain} -os {linux} -proc {psu_cortexa53} -display-name {linux_domain} -desc {Linux Domain} -runtime {opencl} -arch {64-bit}
domain config -boot [file normalize ./platform/boot]
domain config -bif [file normalize ./platform/linux.bif]
domain config -image [file normalize ./platform/image]
domain config -sysroot [file normalize ./platform/sysroots/aarch64-xilinx-linux]
domain config -qemu-data [file normalize ./platform/emulation]
domain config -qemu-args [file normalize ./platform/qemu_args.txt]
domain config -pmuqemu-args [file normalize ./platform/pmu_args.txt]
domain config -bootmode {sd}
domain config -rootfs [file normalize ./petalinux/images/linux/rootfs.ext4]

# Save and build platform
platform write
platform generate
