(*Jan Wielgus*)
(**)

let swap tab i j =
    let aux = tab.(i) in tab.(i) <- tab.(j); tab.(j) <- aux;;
    let choose_pivot tab m n = tab.((m+n)/2);;
    let partition tab l r =
    let i=ref l and j=ref r and pivot=choose_pivot tab l r
    in while !i <= !j do
    while tab.(!i) < pivot do incr i done;
    while pivot < tab.(!j) do decr j done;
    if !i <= !j
    then begin swap tab !i !j; incr i; decr j end
    done;
    (!i,!j)
    ;;

let rec quick tab l r =
    if l < r then
    let (i,j) = partition tab l r
    in if j-l < r-i (* usprawnienie 3 *)
    then let _ = quick tab l j in quick tab i r
    else let _ = quick tab i r in quick tab l j
    else ();;
let quicksort tab = quick tab 0 ((Array.length tab)-1);;
        




(* zadanie 1*)
let zgadnij() =
    let askForNumber() =
        let _ = print_string "Podaj liczbe: " in
        read_int()
        in

    let random = Random.int 100 in

    let choosen = ref (askForNumber()) in

    while !choosen <> random do
        if !choosen > random then
            print_string "Moja jest mniejsza\n"
        else
            print_string "Moja jest wieksza\n";
        choosen := (askForNumber())
    done;

    print_string "Zgadles. Brawo!\n"
    ;;

zgadnij();;



(* zadanie 2 *)
let isspace ch = (ch =' ') || (ch ='\t') || (ch ='\n') || (ch = '\r') ;;
let input_string channel =
    let s = ref "" and ch = ref (input_char channel)
    in
    begin
        while isspace (!ch) do ch := input_char channel done;
        while not (isspace (!ch)) do
            s := !s^(String.make 1 !ch);
            ch := input_char channel
        done;
    !s
    end
    ;;


let sortujPlik() =
    let _ = print_string "Podaj nazwe pliku wejsciowego: " in
    let inputStream = open_in (read_line()) in
    let arrSize = int_of_string (input_string inputStream) in
    let arr = Array.make arrSize 0.0 in
    let idx = ref 0 in

    while !idx < arrSize do
        let _ = (arr.(!idx) <- float_of_string (input_string inputStream)) in
        idx := (!idx + 1)
    done;

    let _ = close_in inputStream in
    let _ = quicksort arr in

    let _ = print_string "Podaj nazwe pliku wyjsciowego: " in
    let outputStream = open_out (read_line()) in
    
    let _ = idx := 0 in
    while !idx < arrSize do
        let _ = output_string outputStream (string_of_float (arr.(!idx))) in
        idx := (!idx + 1)
    done;

    close_out outputStream
    ;;

sortujPlik();;





