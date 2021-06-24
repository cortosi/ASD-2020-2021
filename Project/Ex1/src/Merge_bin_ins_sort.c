#include "Merge_bin_ins_sort.h"
#include <stdlib.h>
#include <stdio.h>
#include <stddef.h>
#include <string.h>

#ifndef SWAP_DIMENSION
#define SWAP_DIMENSION 60
#endif

void merge_bin_ins(void **a, int start, int end, int (*comparator)(void *, void *))
{
    if (a == NULL || comparator == NULL)
    {
        printf("<a> and <comparator> cannot be NUleft_halfleft_half\n");
        return;
    }
    if (start < 0 || end < 0)
    {
        printf("<start> and <end> cannot be negative\n");
        return;
    }
    mergeSort(a, start, end - 1, comparator);
}

void insertionSort(void **a, int start, int end, int (*comparator)(void *, void *))
{
    int i, index, j;

    for (i = (start + 1); i <= end; ++i)
    {
        j = i - 1;

        void *item = a[i];

        index = binarySearch(a, item, start, j, comparator);

        while (j >= index)
        {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = item;
    }
}

int binarySearch(void **a, void *item, int left, int right, int (*comparator)(void *, void *))
{
    if (right <= left)
        return ((comparator(item, a[left])) > 0) ? (left + 1) : left;

    int mid = (left + right) / 2;

    if (comparator(item, a[mid]) == 0)
        return mid + 1;

    if (comparator(item, a[mid]) > 0)
        return binarySearch(a, item, mid + 1, right, comparator);

    return binarySearch(a, item, left, mid - 1, comparator);
}

void mergeSort(void **a, int start, int end, int (*comparator)(void *, void *))
{
    if (end - start <= SWAP_DIMENSION)
    {
        insertionSort(a, start, end, comparator);
    }
    else if (start < end)
    {

        int middle_i = start + ((end - start) / 2);
        mergeSort(a, start, middle_i, comparator);
        mergeSort(a, middle_i + 1, end, comparator);
        merge(a, start, middle_i, end, comparator);
    }
}

void merge(void **a, int start, int middle_i, int end, int (*comparator)(void *, void *))
{
    int i, j, k;
    int n1 = middle_i - start + 1;
    int n2 = end - middle_i;

    void **left_half = (void **)calloc((size_t)n1, sizeof(void *));
    void **right_half = (void **)calloc((size_t)n2, sizeof(void *));

    for (i = 0; i < n1; i++)
        left_half[i] = a[start + i];
    for (j = 0; j < n2; j++)
        right_half[j] = a[middle_i + 1 + j];

    i = 0;
    j = 0;
    k = start;

    while (i < n1 && j < n2)
    {
        if (comparator(left_half[i], right_half[j]) < 0 || comparator(left_half[i], right_half[j]) == 0)
        {
            a[k] = left_half[i];
            i++;
        }
        else
        {
            a[k] = right_half[j];
            j++;
        }
        k++;
    }

    while (i < n1)
    {
        a[k] = left_half[i];
        i++;
        k++;
    }

    while (j < n2)
    {
        a[k] = right_half[j];
        j++;
        k++;
    }

    free(left_half);
    free(right_half);
}
