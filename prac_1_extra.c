#include <stdio.h>
#include <string.h>

int main() {
    char str[20];
    int len;
    
    printf("Enter String: ");
    scanf("%s", str);
    
    len = strlen(str);
    
    if (len < 3) { 
        printf("Invalid String.\n");
        return 0;
    }
    
    if (str[len - 1] != 'b' || str[len - 2] != 'b') {
        printf("Invalid String.\n");
        return 0;
    }
    
    for (int i = 0; i < len - 2; i++) {
        if (str[i] != 'a') {
            printf("Invalid String.\n");
            return 0;
        }
    }
    
    printf("String accepted.\n");
    return 0;
}