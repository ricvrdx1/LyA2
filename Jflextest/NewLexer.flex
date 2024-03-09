%%
%class NewLexer
%type Token

L = [a-z]+
D = [0-9]+
ALFANUM = [a-zA-Z0-9]
DOMINIO = [a-zA-Z0-9.-]
white=[ \t\r\n]+

local1 = {D}{2}("-"){D}{2}("-"){D}{2}("-"){D}{2}
local2 = "(" {D}{2} ")" "-" {D}{2} "-" {D}{2} "-" {D}{2} "-" {D}{2}

cel1 = {D}{2} "-" {D}{2} "-" {D}{2} "-" {D}{2} "-" {D}{2}
cel2 = {D}{3} "-" {D}{2} "-" {D}{2} "-" {D}{2} "-" {D}{2}
cel3 = {D}{3} "-" {D}{2} "-" {D}{2} "-" {D}{2} "-" {D}{2}

mail = {L}+ "@" ({L}+ ("." {L}+)*) | {L}+ ("." {L}+) "@" ({L}+ ("." {L}+)*)*

m = "5" ("1"|"2"|"3"|"4"|"5") {D}{13}
v = "4" {D}{15}

%{
	public String lexeme;
%}
%%

{local1} {lexeme=yytext(); return TELOCAL; }
{local2} {lexeme=yytext(); return TELOCAL; }

{cel1} {lexeme=yytext(); return TELCEL; }
{cel2} {lexeme=yytext(); return TELCEL; }
{cel3} {lexeme=yytext(); return TELCEL; }

{mail} {lexeme=yytext(); return CORREO; }

{m} {lexeme=yytext(); return MASTERCARD; }
{v} {lexeme=yytext(); return VISA; }
