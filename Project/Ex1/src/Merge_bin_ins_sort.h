#ifndef Merge_bin_ins_sort_h
#define Merge_bin_ins_sort_h

#include <stdio.h>

void merge_bin_ins(void **a, int start, int end, int (*comparator)(void *, void *));

void insertionSort(void **a, int start, int end, int (*comparator)(void *, void *));

int binarySearch(void **a, void *item, int left, int right, int (*comparator)(void *, void *));

void merge(void **a, int start, int middle_i, int end, int (*comparator)(void *, void *));

void mergeSort(void **a, int start, int end, int (*comparator)(void *, void *));

#endif
