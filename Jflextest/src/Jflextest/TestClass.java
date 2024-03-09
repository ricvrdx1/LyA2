package Jflextest;

import java.io.*;

import javax.swing.JOptionPane;

public class TestClass {

    public static void main(String args[]) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("texto.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String str = sb.toString();

            InputStream is = new ByteArrayInputStream(str.getBytes());
            NewLexer lexer = new NewLexer(is);

            String resultados = "";

            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    resultados += "FIN";
                    break;
                }
                
                System.out.println("Entrada actual: " + lexer.lexeme);


                switch (token) {
                    case TELOCAL:
                        resultados += "Telefono Local: " + token + " " + lexer.lexeme + "\n";
                        break;

                    case TELCEL:
                        resultados += "Telefono Celular: " + token + " " + lexer.lexeme + "\n";
                        break;

                    case CORREO:
                        resultados += "Correo Electronico: " + token + " " + lexer.lexeme + "\n";
                        break;

                    case MASTERCARD:
                        resultados += "Tarjeta Mastercard: " + token + " " + lexer.lexeme + "\n";
                        break;

                    case VISA:
                        resultados += "Tarjeta Visa: " + token + " " + lexer.lexeme + "\n";
                        break;

                    default:
                        resultados += "Error: " + token + "\n";
                        break;
                }
            }

            guardarResultadosEnHTML(resultados);

            JOptionPane.showMessageDialog(null, "Resultados guardados en el archivo 'output.html'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarResultadosEnHTML(String resultados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.html"))) {
            writer.write("<html><body>\n");
            writer.write("<pre>\n");
            writer.write(resultados);
            writer.write("</pre>\n");
            writer.write("</body></html>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
