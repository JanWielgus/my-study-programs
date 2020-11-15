(*
Wskazowki:
- sprawdzaj czy wyodrebnione dzialanie dla pojedynczego elementu listy ma sens 
- jesli operacje sa na wartosciach boolowskich to nie uzywaj ifow tylko operatorow logicznych
- sprawdzaj czy wszystko jest dokladnie tak jak w specyfikacji

*)



(*zadanie 1 lista 2*)
let rec take x a =
  match x with
  | x when x <= 0 -> []
  | 1 -> if a = [] then [] else [List.hd a]
  | x -> if a = [] then []
      else List.hd a :: take (x-1) (List.tl a)
  ;;

