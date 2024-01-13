#pragma qtrvsim show registers
#pragma qtrvsim show program
#pragma qtrvsim show memory

.globl _start
.option norelax
.text
# ------------------------------------------------------------
_start:
# ------------------------------------------------------------
    # feel free to adjust values for testing
    # uncomment for local testing:
    jal initialize_jump_table
    la a0, concat_string
    jal print_digit
# ------------------------------------------------------------
    ebreak

initialize_jump_table:
# ------------------------------------------------------------
    la t0, number_string_table
    la t1, string_one
    sw t1, 4(t0)
    la t1, string_two
    sw t1, 8(t0)
    la t1, string_three
    sw t1, 12(t0)
    la t1, string_four
    sw t1, 16(t0)
    la t1, string_five
    sw t1, 20(t0)
    la t1, string_six
    sw t1, 24(t0)
    la t1, string_seven
    sw t1, 28(t0)
    la t1, string_eight
    sw t1, 32(t0)
    la t1, string_nine
    sw t1, 36(t0)
# ------------------------------------------------------------    
    ret

# ------------------------------------------------------------
# submission begin
# ------------------------------------------------------------
# arguments:
#   a0: pointer to digit
# returns:
#   a0: pointer to character after digit
print_digit:
# ------------------------------------------------------------
# your code start
# ------------------------------------------------------------
    # TODO: implement function
    lb t0, 0(a0) # load first char (size 1B)
    addi t0, t0, -48 # convert ascii representation to numeric
    li t3, 4 # constant 4 for mul
    mul t2, t0, t3 # calculate index offset for array access
    la t1, number_string_table # load table address
    add t1, t1, t2 # add calculated index offset to address
    lw t4, 0(t1) # load address of the corresponding string

    mv s0, ra
    mv s1, a0
    mv a0, t4
    jal print_string
    mv ra, s0
    mv a0, s1

    addi a0, a0, 1
    ret
# ------------------------------------------------------------
# your code end
# ------------------------------------------------------------

# arguments:
#   a0: pointer to multiple group
# returns:
#   a0: pointer to character after multiple group
print_multiple:
# ------------------------------------------------------------
# your code start
# ------------------------------------------------------------
    # TODO: implement function
    mv s4, ra
    jal print_digit
    jal print_subject
    mv ra, s4
    ret
# ------------------------------------------------------------
# your code end
# ------------------------------------------------------------

# arguments:
#   a0: pointer to subject group
# returns:
#   a0: pointer to character after subject group
print_subject:
# ------------------------------------------------------------
# your code start
# ------------------------------------------------------------
    # TODO: implement function
    lb t0, 0(a0) # load first char (size 1B)
    li t1, 99 # c
    beq t0, t1, print_subject_cat
    li t1, 64 # @
    beq t0, t1, print_subject_at
    li t1, 91 # [
    beq t0, t1, print_subject_box_open
print_subject_cat:
    mv s5, ra
    mv s6, a0
    la a0, string_cat
    jal print_string
    mv ra, s5
    mv a0, s6
    
    addi a0, a0, 3
    ret
print_subject_at:
    mv s5, ra
    mv s6, a0
    la a0, string_yarn
    jal print_string
    mv ra, s5
    mv a0, s6
    
    addi a0, a0, 1
    ret
print_subject_box_open:
    addi a0, a0, 1
    lb t0, 0(a0) # load first char (size 1B)    

    li t1, 93 # ]
    beq t0, t1, print_subject_empty_box

    # print box(es)
    mv s5, ra
    mv s6, a0
    la a0, string_box
    jal print_string
    mv ra, s5
    mv a0, s6

    # print containing
    mv s5, ra
    mv s6, a0
    la a0, string_containing
    jal print_string
    mv ra, s5
    mv a0, s6
    
    # call concat recursively
    addi sp, sp, -4
    sw ra, 0(sp)
    jal print_concat
    lw ra, 0(sp)
    addi sp, sp, 4

    addi a0, a0, 1
    ret
print_subject_empty_box:
    mv s5, ra
    mv s6, a0
    la a0, string_box
    jal print_string
    mv ra, s5
    mv a0, s6
    
    addi a0, a0, 2
    ret
# ------------------------------------------------------------
# your code end
# ------------------------------------------------------------

# arguments:
#   a0: pointer to concat string
# returns:
#   a0: pointer to character after concat string
print_concat:
# ------------------------------------------------------------
# your code start
# ------------------------------------------------------------
    # TODO: implement function
    lb t0, 0(a0) # load first char (size 1B)
    
    li t1, 49 # 1
    beq t0, t1, print_concat_multiple
    li t1, 50
    beq t0, t1, print_concat_multiple
    li t1, 51
    beq t0, t1, print_concat_multiple
    li t1, 52
    beq t0, t1, print_concat_multiple
    li t1, 53
    beq t0, t1, print_concat_multiple
    li t1, 54
    beq t0, t1, print_concat_multiple
    li t1, 55
    beq t0, t1, print_concat_multiple
    li t1, 56
    beq t0, t1, print_concat_multiple
    li t1, 57
    beq t0, t1, print_concat_multiple
    
    j print_concat_subject
print_concat_multiple:
    mv s5, ra
    jal print_multiple
    mv ra, s5

    lb t0, 0(a0) # load first char (size 1B)
    li t1, 43
    beq t0, t1, print_concat_plus

    ret
print_concat_subject:
    mv s5, ra
    jal print_subject
    mv ra, s5

    lb t0, 0(a0) # load first char (size 1B)
    li t1, 43
    beq t0, t1, print_concat_plus

    ret
print_concat_plus:
    mv s8, ra
    mv s9, a0
    la a0, string_next_to
    jal print_string
    mv ra, s8
    mv a0, s9
    
    mv s7, ra
    jal print_concat
    mv ra, s7
    ret
# ------------------------------------------------------------
# your code end
# ------------------------------------------------------------
# submission end
# ------------------------------------------------------------

# arguments:
#   a0: pointer to string struct
print_string:
    # reserve space on stack
    addi sp, sp, -4
    # store return address
    sw ra, 0(sp)
    # load length
    lw a2, -4(a0)
    add a1, a0, zero

    # load syscall number
    addi  a7, zero, 64
    # load file descriptor
    addi  a0, zero, 1
    # print the text
    ecall

    # load return address
    lw ra, 0(sp)
    # free stack space
    addi sp, sp, 4
    # return
    jalr zero, 0(ra)

# ------------------------------------------------------------
# data section
# ------------------------------------------------------------
.data
.org 0x800
# ------------------------------------------------------------
concat_string: .asciz "1[8@+1cat]+[cat+@]"
#pragma qtrvsim focus memory concat_string
# ------------------------------------------------------------
.word 4
string_one:
.ascii "one "
# ------------------------------------------------------------
.word 4
string_two:
.ascii "two "
# ------------------------------------------------------------
.word 6
string_three:
.ascii "three "
# ------------------------------------------------------------
.word 5
string_four:
.ascii "four "
# ------------------------------------------------------------
.word 5
string_five:
.ascii "five "
# ------------------------------------------------------------
.word 4
string_six:
.ascii "six "
# ------------------------------------------------------------
.word 6
string_seven:
.ascii "seven "
# ------------------------------------------------------------
.word 6
string_eight:
.ascii "eight "
# ------------------------------------------------------------
.word 5
string_nine:
.ascii "nine "
# ------------------------------------------------------------
.word 8
string_yarn:
.ascii "yarn(s) "
# ------------------------------------------------------------
.word 7
string_cat:
.ascii "cat(s) "
# ------------------------------------------------------------
.word 8
string_box:
.ascii "box(es) "
# ------------------------------------------------------------
.word 11
string_containing:
.ascii "containing "
# ------------------------------------------------------------
.word 8
string_next_to:
.ascii "next to "
# ------------------------------------------------------------
.word  0
number_string_table: .word  0, string_one, string_two, string_three, string_four, string_five, string_six, string_seven, string_eight, string_nine
# ------------------------------------------------------------