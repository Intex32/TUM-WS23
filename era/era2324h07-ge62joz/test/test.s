_start:  
        addi x5, x0, 0
        addi x6, x0, 10
loop_start:          
        addi x5, x5, 1
        beq x5, x6, .+8
        jal x0, .-8
loop_end:       
        addi x7, x0, 25
        sw x7, 100(x0)
        sw x5, 64(x0)
        ecall
