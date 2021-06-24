#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <ctype.h>

#include "Edit_distance.h"

int main(int argc, char **argv)
{

    // Checking open file stream
    FILE *correctme = fopen("./correctme.txt", "r+");
    if (correctme == NULL)
    {
        printf("Err: Cannot open correctme.txt FILE\n");
        exit(EXIT_FAILURE);
    }

    FILE *dictionary = fopen("./dictionary.txt", "r+");
    if (dictionary == NULL)
    {
        printf("Err: Cannot open dictionary.txt FILE\n");
        exit(EXIT_FAILURE);
    }

    // Vars
    int act;
    int min_edit = INT_MAX;
    char *tok;
    char word[1024];
    char choosen[1024];
    char input[1024];

    // Reading the sentence that needs to be corrected
    fscanf(correctme, "%[^\n]", input);

    // Tokenizing the sentence and find out the Edit-distance
    tok = strtok(input, ",.\t ");
    while (tok != NULL)
    {
        tok[0] = (char)tolower(tok[0]);
        while (fscanf(dictionary, "%[^\n]", word) != EOF)
        {
            fgetc(dictionary);
            if ((act = edit_distance_dyn(tok, word)) < min_edit)
            {
                min_edit = act;
                strcpy(choosen, word);
            }
        }
        printf("Word: %s, minimum edit-distance: %s\n", tok, choosen);
        tok = strtok(NULL, ",.\t ");
        min_edit = INT_MAX;
        fseek(dictionary, 0, SEEK_SET);
    }

    fclose(correctme);
    fclose(dictionary);

    return 0;
}
