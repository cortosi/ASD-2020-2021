#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "unity_internals.h"
#include "Edit_distance.h"

static void test_null_params_edit_distance()
{
    TEST_ASSERT_EQUAL_INT(-1, edit_distance_dyn(NULL, NULL));
}

static void test_empty_string_edit_distance()
{
    TEST_ASSERT_EQUAL_INT(0, edit_distance_dyn("", ""));
}

static void test_zero_edit_distance()
{
    TEST_ASSERT_EQUAL_INT(0, edit_distance_dyn("hello", "hello"));
}

static void test_two_edit_distance()
{
    TEST_ASSERT_EQUAL_INT(2, edit_distance_dyn("hello", "helto"));
}

static void test_ten_edit_distance()
{
    TEST_ASSERT_EQUAL_INT(10, edit_distance_dyn("", "0123456789"));
}

int main(int argc, char const *argv[])
{
    UNITY_BEGIN();
    RUN_TEST(test_null_params_edit_distance);
    RUN_TEST(test_empty_string_edit_distance);
    RUN_TEST(test_zero_edit_distance);
    RUN_TEST(test_two_edit_distance);
    RUN_TEST(test_ten_edit_distance);
    UNITY_END();
    return 0;
}
