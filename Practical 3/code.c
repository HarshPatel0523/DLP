/* salary calculation */
void main() {
    long int bs, da, hra, gs;
    // Take basic salary as input
    scanf("%ld", &bs);

    // Calculate allowances
    da = bs * 0.40;
    hra = bs * 0.20;
    gs = bs + da + hra;

    // Display salary slip
    printf("\nbs : %ld", bs);
    printf("\nda : %ld", da);
    printf("\nhra : %ld", hra);
    printf("\ngs : %ld", gs);
}