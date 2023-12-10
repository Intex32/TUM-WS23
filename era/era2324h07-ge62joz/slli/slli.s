_start:
        addi t0, zero, 1
        addi t1, t1, 512
        addi t3, zero, 0
loop_begin:
        slli t0, t0, 3
        addi t3, t3, 1
        beq t0, t1, loop_end
        jal x0, loop_begin
loop_end:
        sw t0, 64(x0) # 512 should be at word 64
        sw t3, 68(x0) # 3 should be at word 68
        ecall
