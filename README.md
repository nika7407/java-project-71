# File Differ (gendiff)

## Overview

`gendiff` is a command-line tool that compares two configuration files (JSON or YAML) and displays the differences. The tool supports three output formats: **JSON**, **Stylish**, and **Plain**.

### Hexlet Tests and Linter Status:
[![Actions Status](https://github.com/nika7407/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/nika7407/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/51aff2c0c4a889145241/maintainability)](https://codeclimate.com/github/nika7407/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/51aff2c0c4a889145241/test_coverage)](https://codeclimate.com/github/nika7407/java-project-71/test_coverage)

## Features
- **Compare JSON and YAML files**: Shows the differences between two configuration files.
- **Multiple Output Formats**: Supports three output formats for showing the difference: 
    - **Stylish** (default)
    - **Plain**
    - **JSON**
  
### Example

You can see how it works in practice with the following [asciinema recording](https://asciinema.org/a/G5oHNch8qkW6UbAsHjGw3aNAT):

[![asciicast](https://asciinema.org/a/G5oHNch8qkW6UbAsHjGw3aNAT.svg)](https://asciinema.org/a/G5oHNch8qkW6UbAsHjGw3aNAT)

---

## Installation and Usage

### Command-Line Usage:
The `gendiff` tool can be run using the following command:

```bash
gendiff [-hV] [-f=format] filepath1 filepath2

