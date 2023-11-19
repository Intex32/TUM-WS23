#pragma qtrvsim show registers
#pragma qtrvsim show program
# ------------------------------------------------------------
.globl _start
.text
_start:
# ------------------------------------------------------------
    # load 11 as argument for tribonacci
    li a0, 8
    # call tribonacci
    jal tribonacci
    # break for debug
    ebreak
# ------------------------------------------------------------
# submission begin
# ------------------------------------------------------------
# arguments:
#   a0: N
# returns:
#   a0: N-th tribonacci number
tribonacci:
# ------------------------------------------------------------
# your code start
# ------------------------------------------------------------
    # TODO: implement function
li t0, 2
beq a0, t0, exit_one
li t0, 1
beq a0, t0, exit_zero
li t0, 0
beq a0, t0, exit_zero

addi sp, sp, -32
sw ra, 0(sp)
sw a0, 4(sp)

addi a0, a0, -1
jal ra, tribonacci
sw a0, 8(sp)

lw a0, 4(sp)
addi a0, a0, -2
jal ra, tribonacci
sw a0, 12(sp)

lw a0, 4(sp)
addi a0, a0, -3
jal ra, tribonacci
sw a0, 16(sp)

lw t0, 8(sp)
lw t1, 12(sp)
lw t2, 16(sp)
add a0, t0, t1
add a0, a0, t2

lw ra, 0(sp)
addi sp, sp, 32
jalr zero, 0(ra)

exit_zero:
li a0, 0
jalr zero, 0(ra)

exit_one:
li a0, 1
jalr zero, 0(ra)
# ------------------------------------------------------------
# your code end
# -----------------------------------------------------------
# submission end
# ------------------------------------------------------------