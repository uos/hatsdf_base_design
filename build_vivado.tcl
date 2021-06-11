# Open project
open_project FastSense_base/FastSense_base.xpr

# Reset runs
reset_runs synth_1
reset_runs impl_1

# Implement desgn and generate bitstream
launch_runs impl_1 -to_step write_bitstream -jobs 8
wait_on_run impl_1

# Generate XSA file
write_hw_platform -include_bit -force FastSense_base/FastSense_base.xsa

close_project
