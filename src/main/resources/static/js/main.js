var app = new Vue({
    el: '#app',
    data: {
        rows: [],
        cols: [],
        isCellCovered: true,
        isCellUncovered: false,
        displayText: '',
        firstClick: false,
        i:0,
        j:0
    },
    methods: {
            uncoverCell(idx1, idx2) {
                if (!this.firstClick){
                    axios.get('http://localhost:8080/board/create?x='+idx1+'&y='+idx2)
                                    .then(response => (
                                        this.rows = response.data.map(row => (
                                            this.cols = row.map (col => ( col==0 ?
                                               { showed:'', value: col, isCellCovered:false, isCellUncovered: true, ...col }
                                               :
                                               { showed:'', value: col, isCellCovered:true, isCellUncovered: false, ...col }
                                            ))
                                        )),
                                        this.rows[idx1][idx2].isCellCovered = false,
                                        this.rows[idx1][idx2].isCellUncovered = true,
                                        this.rows[idx1][idx2].isCellMarked = false,
                                        this.firstClick = true
                                    )
                    )
            }
            else {
                this.rows[idx1][idx2].isCellCovered = false
                this.rows[idx1][idx2].isCellUncovered = true
                this.rows[idx1][idx2].isCellMarked = false
                if (this.rows[idx1][idx2].value == '9') {
                    this.rows[idx1][idx2].showed = '💥';
                }
                else if (this.rows[idx1][idx2].value == '0') {
                    this.rows[idx1][idx2].showed = '';
                }
                else {
                    this.rows[idx1][idx2].showed = this.rows[idx1][idx2].value;
                }
            }
        },
        rightClickCell(idx1, idx2) {
            if (this.rows[idx1][idx2].isCellCovered) {
                this.rows[idx1][idx2].isCellMarked = true
                this.rows[idx1][idx2].isCellCovered = false
                this.rows[idx1][idx2].isCellUncovered = false
            }
            else if (this.rows[idx1][idx2].isCellMarked) {
                this.rows[idx1][idx2].isCellMarked = false
                this.rows[idx1][idx2].isCellCovered = true
                this.rows[idx1][idx2].isCellUncovered = false
            }
        }
    },
    mounted() {
        axios.get('http://localhost:8080/board/clear')
            .then(response => (
                this.rows = response.data.map(row => (
                    this.cols = row.map (col => (
                       { showed:'', value: col, isCellCovered: true, ...col }
                    ))
                ))
            ))
    }
})


