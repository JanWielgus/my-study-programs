(*Jan Wielgus*)
(**)

type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t;;



(*zadanie 1*)
let rec lrepeat k 
