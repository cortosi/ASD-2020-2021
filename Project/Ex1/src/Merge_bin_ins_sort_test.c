#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "unity_internals.h"
#include "Merge_bin_ins_sort.h"
#include "Comparators.h"

static int v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;

static double d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;

void setUp(void)
{
    v1 = 1;
    v2 = 2;
    v3 = 3;
    v4 = 4;
    v5 = 5;
    v6 = 6;
    v7 = 7;
    v8 = 8;
    v9 = 9;
    v10 = 10;

    d1 = 1.0;
    d2 = 2.0;
    d3 = 3.0;
    d4 = 4.0;
    d5 = 5.0;
    d6 = 6.0;
    d7 = 7.0;
    d8 = 8.0;
    d9 = 9.0;
    d10 = 10.0;
}

static void test_one_element_int(void)
{
    int *input[] = {&v1};
    int *expected[] = {&v1};
    merge_bin_ins((void **)input, 0, 1, _Int_comparator);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_element_int(void)
{
    int *input[] = {&v10, &v9, &v8, &v7, &v6, &v5, &v4, &v3, &v2, &v1};
    int *expected[] = {&v1, &v2, &v3, &v4, &v5, &v6, &v7, &v8, &v9, &v10};
    merge_bin_ins((void **)input, 0, 10, _Int_comparator);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 10);
}

static void test_one_element_double(void)
{
    double *input[] = {&d1};
    double *expected[] = {&d1};
    merge_bin_ins((void **)input, 0, 1, _Double_comparator);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_element_double(void)
{
    double *input[] = {&d10, &d9, &d8, &d7, &d6, &d5, &d4, &d3, &d2, &d1};
    double *expected[] = {&d1, &d2, &d3, &d4, &d5, &d6, &d7, &d8, &d9, &d10};
    merge_bin_ins((void **)input, 0, 10, _Double_comparator);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 10);
}

static void test_one_element_chars(void)
{
    char *input[] = {"a"};
    char *expected[] = {"a"};
    merge_bin_ins((void **)input, 0, 1, _Char_comparator);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
}

static void test_ten_element_chars(void)
{
    char *input[] = {"l", "i", "h", "g", "f", "e", "d", "c", "b", "a"};
    char *expected[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "l"};
    merge_bin_ins((void **)input, 0, 10, _Char_comparator);
    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 10);
}

int main(int argc, char const *argv[])
{
    UNITY_BEGIN();
    RUN_TEST(test_one_element_int);
    RUN_TEST(test_ten_element_int);
    RUN_TEST(test_one_element_double);
    RUN_TEST(test_ten_element_double);
    RUN_TEST(test_one_element_chars);
    RUN_TEST(test_ten_element_chars);
    return UNITY_END();
}
