# EncDecPython

## Summary
This project demonstrates encryption and decryption operations using Triple DES (3DES) with CBC mode. It integrates Java for invoking Python scripts that handle the cryptographic operations. The Python script encrypts and decrypts data based on input provided by the Java application.

## Features
- **Encryption**: Encrypt data using Triple DES with CBC mode.
- **Decryption**: Decrypt encrypted data using Triple DES with CBC mode.
- **Integration**: Java application interacts with Python script for cryptographic operations.
  
## Usage
To use this project:
1. Ensure Python is installed on your system.
2. Run the Java application `EncDecUtil.java` to encrypt or decrypt data.
3. Pass appropriate parameters (data, key, IV) to encrypt or decrypt data.
4. The Python script (`EncDecUtil.py`) handles the cryptographic operations and returns the result to the Java application.

## Dependencies
- Python
- PyCryptodome library for Python (`pip install pycryptodome`)

