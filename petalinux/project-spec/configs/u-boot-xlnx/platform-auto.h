/*
 * This file is auto-generated by PetaLinux SDK 
 * DO NOT MODIFY this file, the modification will not persist
 */

#ifndef __PLNX_CONFIG_H
#define __PLNX_CONFIG_H

/* The following table includes the supported baudrates */


#define CONFIG_SYS_BAUDRATE_TABLE  { 4800, 9600, 19200, 38400, 57600, 115200 }



/* processor - psu_cortexa53_0 */
#define CONFIG_CPU_ARMV8
#define CONFIG_CLOCKS
#define CONFIG_REMAKE_ELF
#define CONFIG_BOARD_EARLY_INIT_F
#define CONFIG_ARM_DCC
#define CONFIG_MP

/* main_memory - psu_ddr_0 */

/* uart - psu_uart_0 */
#define PSSERIAL0	"psserial0=setenv stdout ttyPS0;setenv stdin ttyPS0\0"
#define SERIAL_MULTI	"serial=setenv stdout serial;setenv stdin serial\0"
#define CONSOLE_ARG	"console=console=ttyPS0,115200\0"
#define SERIAL_MULTI  "serial=setenv stdout serial;setenv stdin serial\0"
#define CONFIG_BAUDRATE	115200

/* ethernet - psu_ethernet_3 */
#define CONFIG_SYS_FAULT_ECHO_LINK_DOWN
#define PHY_ANEG_TIMEOUT 20000
#define CONFIG_MII
#define CONFIG_NET_MULTI
#define CONFIG_NETCONSOLE	1
#define CONFIG_IPADDR

/* spi_flash - psu_qspi_0 */
#define XILINX_PS8_QSPI_CLK_FREQ_HZ	300000000
#define CONFIG_SF_DEFAULT_SPEED   (XILINX_PS8_QSPI_CLK_FREQ_HZ / 4)
#define CONFIG_MTD_PARTITIONS
#define CONFIG_SPI_FLASH_MTD

/* sdio - psu_sd_1 */
#define CONFIG_SUPPORT_EMMC_BOOT

/* rtc - psu_rtc */

/* sata - psu_sata */
#define CONFIG_LIBATA

/* i2c - psu_i2c_0 */

/* usb - psu_usb_0 */
#define CONFIG_THOR_RESET_OFF
#define CONFIG_SYS_DFU_DATA_BUF_SIZE 0x1800000
#define DFU_DEFAULT_POLL_TIMEOUT 300

/* zynq_ultra_ps_e_0 */
#define COUNTER_FREQUENCY 100000000

/* intc - psu_acpu_gic */
#define ACPU_GIC_BASEADDR	0xF9010000
#define CONFIG_GICV2	1
#define GICD_BASE	(ACPU_GIC_BASEADDR)
#define GICC_BASE (ACPU_GIC_BASEADDR + 0x10000)

/* FPGA */

/* Memory testing handling */
#define CONFIG_SYS_MEMTEST_START	0x0
#define CONFIG_SYS_MEMTEST_END	(0x0 + 0x1000)
#define CONFIG_SYS_LOAD_ADDR	(0x0 + 0x100000) /* default load address */
#define CONFIG_SYS_INIT_SP_ADDR	(CONFIG_SYS_LOAD_ADDR - GENERATED_GBL_DATA_SIZE)
#define CONFIG_NR_DRAM_BANKS	2

/* Size of malloc() pool */
#define SIZE	0x2000000
#define CONFIG_SYS_MALLOC_LEN	SIZE


/* BOOTP options */
#define CONFIG_BOOTP_SERVERIP
#define CONFIG_BOOTP_BOOTFILESIZE
#define CONFIG_BOOTP_BOOTPATH
#define CONFIG_BOOTP_GATEWAY
#define CONFIG_BOOTP_HOSTNAME
#define CONFIG_BOOTP_MAY_FAIL
#define CONFIG_BOOTP_DNS
#define CONFIG_BOOTP_SUBNETMASK
#define CONFIG_BOOTP_PXE

/*Command line configuration.*/
#define CONFIG_CMDLINE_EDITING
#define CONFIG_AUTO_COMPLETE

#define CONFIG_SUPPORT_RAW_INITRD

/* Miscellaneous configurable options */
#define CONFIG_SYS_CBSIZE	2048/* Console I/O Buffer Size      */
#define CONFIG_SYS_PBSIZE	(CONFIG_SYS_CBSIZE + sizeof(CONFIG_SYS_PROMPT) + 16)
#define CONFIG_SYS_BARGSIZE CONFIG_SYS_CBSIZE

/* Use the HUSH parser */
#define CONFIG_SYS_PROMPT_HUSH_PS2 "> "

#define CONFIG_ENV_VARS_UBOOT_CONFIG
#define CONFIG_ENV_OVERWRITE	/* Allow to overwrite the u-boot environment variables */

#define CONFIG_LMB

/* FDT support */
#define CONFIG_DISPLAY_BOARDINFO_LATE


/* Boot Argument Buffer Size */
#define CONFIG_SYS_MAXARGS      64      /* max number of command args */
#define CONFIG_SYS_LONGHELP

/* Initial memory map for Linux */
#define CONFIG_SYS_BOOTMAPSZ 0x8000000

#endif /* __PLNX_CONFIG_H */
