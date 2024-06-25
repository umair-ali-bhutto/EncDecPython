# @author umair.ali
from Crypto.Cipher import DES3
import binascii
import sys

def encrypt_data(plaintext_hex, key_hex, iv_hex):
    """
    Encrypts the given plaintext using Triple DES encryption in CBC mode.
    
    Args:
        plaintext_hex (str): The plaintext in hexadecimal format.
        key_hex (str): The encryption key in hexadecimal format.
        iv_hex (str): The initialization vector (IV) in hexadecimal format.
    
    Returns:
        str: The encrypted data in hexadecimal format, or an error message if encryption fails.
    """
    try:
        # Convert hex data to bytes
        plaintext_bytes = binascii.unhexlify(plaintext_hex)
        
        # Convert hex key and IV to bytes
        key_bytes = binascii.unhexlify(key_hex)
        iv_bytes = binascii.unhexlify(iv_hex)
        
        # Create a Triple DES cipher object
        cipher = DES3.new(key_bytes, DES3.MODE_CBC, iv_bytes)
        
        # Pad the plaintext to be a multiple of 8 bytes (DES3 block size)
        padded_plaintext = _pad(plaintext_bytes)
        
        # Encrypt the padded plaintext
        ciphertext_bytes = cipher.encrypt(padded_plaintext)
        
        # Convert bytes to formatted hexadecimal string
        ciphertext_hex = binascii.hexlify(ciphertext_bytes).decode('utf-8').upper()
        
        return ciphertext_hex
    except Exception as e:
         return "Error in encryption: {}".format(e)

def decrypt_data(ciphertext_hex, key_hex, iv_hex):
    """
    Decrypts the given ciphertext using Triple DES decryption in CBC mode.
    
    Args:
        ciphertext_hex (str): The ciphertext in hexadecimal format.
        key_hex (str): The decryption key in hexadecimal format.
        iv_hex (str): The initialization vector (IV) in hexadecimal format.
    
    Returns:
        str: The decrypted data in hexadecimal format, or an error message if decryption fails.
    """
    try:
        # Convert hex data to bytes
        ciphertext_bytes = binascii.unhexlify(ciphertext_hex)
        
        # Convert hex key and IV to bytes
        key_bytes = binascii.unhexlify(key_hex)
        iv_bytes = binascii.unhexlify(iv_hex)
        
        # Create a Triple DES cipher object
        cipher = DES3.new(key_bytes, DES3.MODE_CBC, iv_bytes)
        
        # Decrypt the data
        plaintext_bytes = cipher.decrypt(ciphertext_bytes)
        
        # Convert bytes to formatted hexadecimal string
        plaintext_hex = binascii.hexlify(plaintext_bytes).decode('utf-8').upper()
        
        return plaintext_hex
    except Exception as e:
        return "Error in decryption: {}".format(e)

def _pad(data):
    """
    Pads the given data to ensure it is a multiple of the block size (8 bytes for DES3).
    
    Args:
        data (bytes): The data to be padded.
    
    Returns:
        bytes: The padded data.
    """
    # PKCS#7 padding
    pad_len = 8 - (len(data) % 8)
    return data + bytes([pad_len] * pad_len)

if __name__ == "__main__":
    if len(sys.argv) < 5:
        print("Usage: python EncDecUtil.py <encrypt/decrypt> <data_hex> <key_hex> <iv_hex>")
        sys.exit(1)
    
    operation = sys.argv[1]
    data_hex = sys.argv[2]
    key_hex = sys.argv[3]
    iv_hex = sys.argv[4]
    
    if not data_hex or not key_hex or not iv_hex:
        print("Error: data_hex, key_hex, and iv_hex cannot be empty.")
        sys.exit(1)
    
    if operation == "encrypt":
        result = encrypt_data(data_hex, key_hex, iv_hex)
    elif operation == "decrypt":
        result = decrypt_data(data_hex, key_hex, iv_hex)
    else:
        print("Invalid operation. Use 'encrypt' for encryption or 'decrypt' for decryption.")
        sys.exit(1)
    
    print(result)

    # Format the hexadecimal string
    # commented by UMAIR.ALI
    # formatted_hex_str = ' '.join(hex_str[i:i+2] for i in range(0, len(hex_str), 2)).upper()
    
    
