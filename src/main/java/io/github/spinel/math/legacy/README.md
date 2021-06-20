# `package io.github.spinel.math.legacy`

## About

See `io.github.spinel.math`

## Developer note : deprecation warning

This package contains some legacy code, which has been refactored long ago, but could still be used to improve the current API (some features haven't been reimplemented yet).

Builds which do at some point use a class defined in this package **should not** be pushed to the main branch.

> Why is this still made available on the main branch ?

Mostly because the new version of the math package has proven to be way less efficient in terms of speed and accuracy than the old one. We'll keep it as long as all the aforementioned issues aren't fixed.

Check the issues section of the repo at https://github.com/l3alr0g/Spinel to stay tuned.
