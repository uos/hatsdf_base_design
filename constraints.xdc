# T0 EX_IO5
# T1 EX_IO6
# T2 EX_IO7
# T3 EX_IO8
set_property PACKAGE_PIN AC12 [get_ports PMOD_IN_tri_i[0]]
set_property PACKAGE_PIN AE8 [get_ports PMOD_IN_tri_i[1]]
set_property PACKAGE_PIN AE7 [get_ports PMOD_OUT_tri_o[0]]
set_property PACKAGE_PIN AE1 [get_ports PMOD_OUT_tri_o[1]]

# T3
# T0
# T1
# T2
set_property PACKAGE_PIN N11 [get_ports PMOD_TYPE[0]]
set_property PACKAGE_PIN V3 [get_ports PMOD_TYPE[1]]
set_property PACKAGE_PIN Y7 [get_ports PMOD_TYPE[2]]
set_property PACKAGE_PIN Y10 [get_ports PMOD_TYPE[3]]

set_property IOSTANDARD LVCMOS18 [get_ports PMOD_IN_tri_i]
set_property IOSTANDARD LVCMOS18 [get_ports PMOD_OUT_tri_o]
set_property IOSTANDARD LVCMOS18 [get_ports PMOD_TYPE]