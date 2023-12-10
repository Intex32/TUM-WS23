_start:   
        addi x5, x0, 0
        addi x6, x0, 0
        addi x5, x5, 24
        addi x7, x0, 0
loop_start:     
        addi x5, x5, -5
        bge x6, x5, loop_end
        addi x7, x7, 1
        jal x0, loop_start
loop_end:       
        sw x7, 100(x0) # 4 should be at word 100
        ecall
