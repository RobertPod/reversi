# reversi - projekt szkoleniowy, żeby opanować Springa, Mavena, Hibenate i Android API

Celem projektu jest oprogramowanie gry Reversi w wersjach: standalone, webowej i mobilnej.

Wersja standalone mieści się w projekcie: <strong>robert.reversi-v2</strong>.
Zawarta tam jest również "inteligencja" gry, dlatego package ten będzie dołączany do pozostałych projektów.
W programie każdy ruch wymaga dwóch kliknięć - bardzo ułatwia to anlizę i doskonalenie algorytmu ruchów komputera. Ambicją autora jest dopracowanie haurystyk algorytmu tak, żeby trudno było wygrać z komputerem. Obecnie grę można określić jako średniołatwą. Działający program oparty o Spring można pobrać z niniejszego repozytorium <a href="https://github.com/RobertPod/reversi/blob/master/robert.reversi-v2/target/reversi_v2.jar">pod adresem (plik JAR)</a>

/* <a href="http://reversiv5web.cfapps.io">Wersja WEB</a> */
Wersja WEB zawarta jest w projekcie <strong>robert.reversi-v5web</strong>. Jest już grywalna ale jeszcze nie kompletna.
Celem projektu jest rozwój kompetencji backendowych - dlatego frontend wygląda jak wygląda - przewidziany jest jednak refaktoring.
Dobór tehnologii (Spring MVC, JPA, Spring Data), wszystkie operacje w backendzie, brak dbałości o frontend (+ technologiaJSP) podporządkowano celowi dydaktycznemu, dlatego proszę nie wyśmiewać - przynajmniej na razie.

W tej chwili aktywne są ekrany:<br />
<strong>&bullet; GameBoard</strong><br />
Wiadomo. Udanych gier.

<strong>&bullet; LogginPageForm</strong><br />
Wiadomo. Obsługę sesji celowo zrobiono na piechotę bo to ciekawsze niż Spring SECURITY.

<strong>&bullet; CreateNewUserForm</strong><br />
Raz w życiu chciałem napisać porządnie zakładanie konta. Dlatego wymóg skomplikowanego hasła, reCapcha itd. Unikanie Spring SECURITY jak wyżej.

<strong>&bullet; GameRulesForm</strong><br />
Zasady gry. Chwilowo linki do stron zewnętrznych. Będzie uzupełnione ASAP.

<strong>&bullet; AboutProjectAndAuthorForm</strong><br />
Chwilowo link do LinkedIn :) i tutaj do GitHub. Będzie uzupełnione ASAP.

<i>I strony dostępne po zalogowaniu.</i><br />
<strong>&bullet; UserPageForm</strong><br />
Dashboard, rozprowadzający sterowanie po innych funkcjach.

<strong>&bullet; LoginPlayersForm</strong><br />
W wersji obecnej listing 20 ostatnich sesji. Wiadomo, trudno oczekiwać, że dwóch graczy zagra w tym samym momencie więc ekran byłby zawsze pusty. Docelowo ekran ma umożliwić grę sieciową dwóch graczy.

<strong>&bullet; YourLoginsForm</strong><br />
Twoje logowania. ASAP.

<strong>&bullet; YourGamesForm</strong><br />
Twoje gry. ASAP. Lista rozgrywek jako wstęp do analizy rozgrywek.


Żywa gra znajduje się pod <a href="http://reversiv5web.cfapps.io">linkiem</a><br />
Kolejne kroki to gra sieciowa dwóch graczy i przegląd rozgrywek.

Po zakończeniu wersji sieciowej <strong>powstanie wersja na Androida</strong> prawdopodobnie w Kotlinie jako języku biorącym najlepsze ze Scali i z Groovy, więc teoretycznie o największych walorach dydaktycznych. Mam ambicję mieć swoją aplikację w Google Play ;)
