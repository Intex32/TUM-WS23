.globl _start
.text
_start:
# uncomment the function you want to test and comment the other part
# ------------------------------------------------------------
    # uncomment following two code lines to test mul_pi_16_16
    # feel free to adjust values for testing
    #li a0, 0x50000      # load 5 in 16.16 format       
    #jal mul_pi_16_16    # compute 5 * pi
# ------------------------------------------------------------
    # uncomment following four code lines to test mul_n_fixed_point
    # feel free to adjust values for testing
    li a0, 0x500           # load 5 in 16.16 format 
    li a1, 0x500           # load 5 in 16.16 format 
    li a2, 4               # load 16
    jal mul_n_fixed_point   # compute 5 * pi
# ------------------------------------------------------------    
    ebreak
# ------------------------------------------------------------

# ------------------------------------------------------------
# submission begin
# ------------------------------------------------------------
# arguments:
#   a0: number to multiply
# returns:
#   a0: numpler multiplied by PI in 16.16 format
mul_pi_16_16:
# ------------------------------------------------------------
# your code start
# ------------------------------------------------------------
    # TODO: implement function
    li t5, 0x3243F
    mul t3, a0, t5
    srli t3, t3, 16
    mulh t4, a0, t5
    slli t4, t4, 16
    xor a0, t3, t4
# ------------------------------------------------------------
    # return
    jalr zero, 0(ra)
# ------------------------------------------------------------
# your code end
# ------------------------------------------------------------

# arguments:
#   a0: first number in (32-N).N format
#   a1: second number in (32-N).N format
#   a2: number of decimal places (N)
# returns:
#   a0: result multiplication in (32-N).N format
mul_n_fixed_point:
# ------------------------------------------------------------
# your code start
# ------------------------------------------------------------
    # TODO: implement function
    mul t3, a0, a1
    srl t3, t3, a2

    li t5, 32
    sub a2, t5, a2

    mulh t4, a0, a1
    sll t4, t4, a2

    xor a0, t3, t4
# ------------------------------------------------------------
# your code end
# ------------------------------------------------------------
    # return
    jalr zero, 0(ra)
# ------------------------------------------------------------
# submission end
# ------------------------------------------------------------
