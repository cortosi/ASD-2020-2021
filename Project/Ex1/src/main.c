#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <time.h>
#include <stdio.h>
#include <stddef.h>
#include <errno.h>
#include "Merge_bin_ins_sort.h"

#define INIT_ARR_SIZE 20000000

typedef struct
{
    int id;
    int _int_val;
    double _double_val;
    char *_char_val;
} data;

int data_int_comparator(void *p1, void *p2);

int data_double_comparator(void *p1, void *p2);

int data_char_comparator(void *p1, void *p2);

int main(int argc, char const *argv[])
{
    int i = 0;
    char *tok;

    data **lines = (data **)calloc(INIT_ARR_SIZE, sizeof(data *));
    char *line = (char *)calloc(100, sizeof(char));

    if (lines == NULL || line == NULL)
    {
        printf("Error: Couldn't allocate memory. [errno: %d, %s]\n", errno, strerror(errno));
        exit(EXIT_FAILURE);
    }

    FILE *_file = fopen("./records.csv", "r");
    if (_file == NULL)
    {
        printf("Error: Cannote open file stream. [errno: %d, %s]\n", errno, strerror(errno));
        exit(EXIT_FAILURE);
    }

    printf("Reading file...\n");
    while (fscanf(_file, "%[^\n]", line) != EOF)
    {
        getc(_file);
        tok = strtok(line, ",");
        while (tok != NULL)
        {
            int index = 0;
            while (index < 4)
            {
                switch (index)
                {
                case 0:
                    lines[i] = calloc(1, sizeof(data));
                    lines[i]->id = i;
                    break;
                case 1:
                    lines[i]->_char_val = strcpy((char *)calloc(strlen(tok) + 1, sizeof(char)), tok);
                    break;
                case 2:
                    lines[i]->_int_val = atoi(tok);
                    break;
                case 3:
                    lines[i]->_double_val = atof(tok);
                    break;
                default:
                    printf("Switch error..\n");
                    exit(EXIT_FAILURE);
                    break;
                }
                tok = strtok(NULL, ",");
                index++;
            }
        }
        i++;
    }
    fclose(_file);

    printf("-----------Sorting Int-----------\n");
    clock_t begin = clock();
    merge_bin_ins((void **)lines, 0, INIT_ARR_SIZE, data_int_comparator);
    clock_t end = clock();
    printf("INT SORTED. Time lapsed: %fs\n", (double)(end - begin) / CLOCKS_PER_SEC);
    printf("Creating Int sorted file...\n\n");
    FILE *_int_ordered = fopen("./int_sorted.csv", "w");
    for (int i = 0; i < INIT_ARR_SIZE; i++)
    {
        fprintf(_int_ordered, "%d,%s,%d,%f\n", lines[i]->id, lines[i]->_char_val, lines[i]->_int_val, lines[i]->_double_val);
    }
    fclose(_int_ordered);

    printf("-----------Sorting Double-----------\n");
    begin = clock();
    merge_bin_ins((void **)lines, 0, INIT_ARR_SIZE, data_double_comparator);
    end = clock();
    printf("DOUBLE SORTED. Time lapsed: %fs\n", (double)(end - begin) / CLOCKS_PER_SEC);
    printf("Creating Double sorted file...\n\n");
    FILE *_double_ordered = fopen("./double_sorted.csv", "w");
    for (int i = 0; i < INIT_ARR_SIZE; i++)
    {
        fprintf(_double_ordered, "%d,%s,%d,%f\n", lines[i]->id, lines[i]->_char_val, lines[i]->_int_val, lines[i]->_double_val);
    }
    fclose(_double_ordered);

    printf("-----------Sorting Char-----------\n");
    begin = clock();
    merge_bin_ins((void **)lines, 0, INIT_ARR_SIZE, data_char_comparator);
    end = clock();
    printf("CHAR SORTED. Time lapsed: %fs\n", (double)(end - begin) / CLOCKS_PER_SEC);
    printf("Creating Char sorted file...\n\n");
    FILE *_char_ordered = fopen("./char_sorted.csv", "w");
    for (int i = 0; i < INIT_ARR_SIZE; i++)
    {
        fprintf(_char_ordered, "%d,%s,%d,%f\n", lines[i]->id, lines[i]->_char_val, lines[i]->_int_val, lines[i]->_double_val);
    }
    fclose(_char_ordered);

    //DEALLOCATING
    for (int i = 0; i < INIT_ARR_SIZE; i++)
    {
        free(lines[i]);
    }

    free(lines);

    return 0;
}

// COMPARATORS' DATA STRUCT
int data_int_comparator(void *p1, void *p2)
{
    if (((data *)p1)->_int_val < ((data *)p2)->_int_val)
        return -1;
    else if (((data *)p1)->_int_val > ((data *)p2)->_int_val)
        return 1;
    else
        return 0;
}

int data_double_comparator(void *p1, void *p2)
{
    if (((data *)p1)->_double_val < ((data *)p2)->_double_val)
        return -1;
    else if (((data *)p1)->_double_val > ((data *)p2)->_double_val)
        return 1;
    else
        return 0;
}

int data_char_comparator(void *p1, void *p2)
{
    return strcmp(((data *)p1)->_char_val, ((data *)p2)->_char_val);
}
