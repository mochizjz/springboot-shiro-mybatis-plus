//package test.webeye.code;
//
//import java.nio.ByteBuffer;
//import java.nio.CharBuffer;
//import java.nio.charset.CharacterCodingException;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Date;
//
//import org.springframework.util.StringUtils;
//
//public class TokenGenerator {
//
//    public static void main(String[] args) {
//		String token = TokenGenerator.generateToken("CCIC","ccic1234");
//        System.out.println(token);
//    }
//
//    public static String generateToken(String userName, String password) {
//        String accountName = StringUtils.trimWhitespace(userName);
//
//        // 1 线下获取用户名和密码  如 用户名为: Glutton 密码为: GluttonPass
//
//        // 2 拼接 密码 + "{用户名}"  如: Glutton{GluttonPass}
//        // 3 对拼接的密码UTF8转码后用SHA-256算法对其散列  如 SHA-256(UTF8("Glutton{GluttonPass}")) 并转成16进制字符串 如 34859b87ad4d1ca933e5e775e3d32702455e6d30fa57c8f230bcb7a306520d0d
//        String storedPassword = encode(password, accountName);
//
//        // 4 获取当前系统时间戳 如 1596185293750 重复步骤三 如: SHA-256(UTF834859b87ad4d1ca933e5e775e3d32702455e6d30fa57c8f230bcb7a306520d0d{1596185293750}"))  得到token如： 062eda4fa09333965d34f88444f72976d62a52a1c04bbd6f3b42adad4e9f912f
//        Long timestamp = new Date().getTime();
//        String token = encode(storedPassword, timestamp.toString());
//
//        //5 拼接(用户名 + ":" + 步骤4中获取的token + ":" + 步骤四中的时间戳) 并Base64编码
//        token = String.join(":", Arrays.asList(accountName, token, timestamp.toString()));
//		System.out.println(token);
//        //6 拼接 "Glutton " + 步骤5的结果
//        return "Glutton " + new String(Base64.getEncoder().encode(token.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
//
//    }
//
//
//    public static String encode(String rawPass, String salt) {
//        String saltedPass = mergePasswordAndSalt(rawPass, salt);
//
//        MessageDigest messageDigest = getMessageDigest();
//
//        byte[] digest = messageDigest.digest(Utf8.encode(saltedPass));
//
//        return new String(Hex.encode(digest));
//    }
//
//    public static String mergePasswordAndSalt(String password, String salt) {
//        assert !StringUtils.isEmpty(password);
//
//        return password + "{" + salt + "}";
//    }
//
//    // return SHA-56
//    public static MessageDigest getMessageDigest() throws IllegalArgumentException {
//        try {
//            return MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalArgumentException("No such algorithm [MD5]", e);
//        }
//    }
//
//
//    public static class Hex {
//        private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                'a', 'b', 'c', 'd', 'e', 'f'};
//
//        public static char[] encode(byte[] bytes) {
//            final int nBytes = bytes.length;
//            char[] result = new char[2 * nBytes];
//
//            int j = 0;
//            for (byte aByte : bytes) {
//                // Char for top 4 bits
//                result[j++] = HEX[(0xF0 & aByte) >>> 4];
//                // Bottom 4
//                result[j++] = HEX[(0x0F & aByte)];
//            }
//
//            return result;
//        }
//
//        public static byte[] decode(CharSequence s) {
//            int nChars = s.length();
//
//            if (nChars % 2 != 0) {
//                throw new IllegalArgumentException("Hex-encoded string must have an even number of characters");
//            }
//
//            byte[] result = new byte[nChars / 2];
//
//            for (int i = 0; i < nChars; i += 2) {
//                int msb = Character.digit(s.charAt(i), 16);
//                int lsb = Character.digit(s.charAt(i + 1), 16);
//
//                if (msb < 0 || lsb < 0) {
//                    throw new IllegalArgumentException(
//                            "Detected a Non-hex character at " + (i + 1) + " or " + (i + 2) + " position");
//                }
//                result[i / 2] = (byte) ((msb << 4) | lsb);
//            }
//            return result;
//        }
//    }
//
//
//    public static class Utf8 {
//
//        private static final Charset CHARSET = StandardCharsets.UTF_8;
//
//        /**
//         * Get the bytes of the String in UTF-8 encoded form.
//         */
//        public static byte[] encode(CharSequence string) {
//            try {
//                ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
//                byte[] bytesCopy = new byte[bytes.limit()];
//                System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());
//
//                return bytesCopy;
//            } catch (CharacterCodingException e) {
//                throw new IllegalArgumentException("Encoding failed", e);
//            }
//        }
//
//        /**
//         * Decode the bytes in UTF-8 form into a String.
//         */
//        public static String decode(byte[] bytes) {
//            try {
//                return CHARSET.newDecoder().decode(ByteBuffer.wrap(bytes)).toString();
//            } catch (CharacterCodingException e) {
//                throw new IllegalArgumentException("Decoding failed", e);
//            }
//        }
//    }
//}
