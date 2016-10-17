# reversi - projekt szkoleniowy, żeby opanować Springa, Mavena, Hibenate i Android API

Celem projektu jest oprogramowanie gry Reversi w wersjach: standalone, webowej i mobilnej.

Wersja standalone mieści się w projekcie: <b>robert.reversi-v2</b>.
Zawarta tam jest również "inteligencja" gry, dlatego package ten będzie dołączany do pozostałych projektów.
W programie każdy ruch wymaga dwóch kliknięć - bardzo ułatwia to anlizę i doskonalenie algorytmu ruchów komputera. Ambicją autora jest dopracowanie haurystyk algorytmu tak, żeby trudno było wygrać z komputerem. Obecnie grę można określić jako średniołatwą. Działający program oparty o Spring można pobrać z niniejszego repozytorium <a href="https://github.com/RobertPod/reversi/blob/master/robert.reversi-v2/target/reversi_v2.jar">pod adresem (plik JAR)</a>

Wersja WEB zawarta jest w projekcie <strong>robert.reversi-v5web</strong>. Jest już grywalna ale jeszcze nie kompletna.
Celem projektu jest rozwój kompetencji backendowych - dlatego frontend wygląda jak wygląda - przewidziany jest jednak refaktoring.
Dobór tehnologii (Spring MVC, JPA, Spring Data), wszystkie operacje w backendzie, brak dbałości o frontend (+ technologiaJSP) podporządkowano celowi dydaktycznemu, dlatego proszę nie wyśmiewać - przynajmniej na razie.

W tej chwili aktywne są ekrany:
<h4>.GameBoard</h4>
Wiadomo. Udanych gier.

<h4>LogginPageForm</h4>
Wiadomo. Obsługę sesji celowo zrobiono na piechotę bo to ciekawsze niż Spring SECURITY.

<h4>CreateNewUserForm</h4>
Raz w życiu chciałem napisać porządnie zakładanie konta. Dlatego wymóg skomplikowanego hasła, reCapcha itd. Unikanie Spring SECURITY jak wyżej.

<h4>GameRulesForm</h4>
Zasady gry. Chwilowo linki do stron zewnętrznych. Będzie uzupełnione ASAP.

<h4>AboutProjectAndAuthorForm</h4>
Chwilowo link do LinkedIn :) i tutaj do GitHub. Będzie uzupełnione ASAP.

I strony dostępne po zalogowaniu.
<h4>UserPageForm</h4>
Dashboard, rozprowadzający sterowanie po innych funkcjach.

<h4>LoginPlayersForm</h4>
W wersji obecnej listing 20 ostatnich sesji. Wiadomo, trudno oczekiwać, że dwóch graczy zagra w tym samym momencie więc ekran byłby zawsze pusty. Docelowo ekran ma umożliwić grę sieciową dwóch graczy.

<h4>YourLoginsForm</h4>
Twoje logowania. ASAP.

<h4>YourGamesForm</h4>
Twoje gry. ASAP. Lista rozgrywek jako wstęp do analizy rozgrywek.


Żywa gra znajduje się pod <a href="http://reversiv5web.cfapps.io">linkiem</a>
Kolejne kroki to gra sieciowa dwóch graczy i przegląd rozgrywek.

Po zakończeniu wersji sieciowej <strong>powstanie wersja na Androida</strong> prawdopodobnie w Kotlinie jako języku biorącym najlepsze ze Scali i z Groovy, więc teoretycznie o największych walorach dydaktycznych. Mam ambicję mieć swoją aplikację w Google Play ;)

